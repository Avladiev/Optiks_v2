package aurelienribon.tweenstudiotest;

import aurelienribon.tweenstudio.Editor;
import aurelienribon.tweenstudio.Property.Field;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class LibGdxTweenStudioEditorX extends Editor {
	// Constants
	private static final Color SELECTED_SPRITE_BOX_COLOR = new Color(0.2f, 0.2f, 0.8f, 1.0f);
	private static final Color MOUSEOVER_SPRITE_BOX_COLOR = new Color(0.2f, 0.2f, 0.8f, 0.3f);
	private static final Color SHADOW_COLOR = new Color(0, 0, 0, 0.4f);
	private static final Color FRAME_COLOR = new Color(0, 0, 0, 0.2f);

	// General
	private final List<Sprite> sprites = new ArrayList<Sprite>();
	private final ImmediateModeRenderer imr = new ImmediateModeRenderer10();
	private final SpriteBatch spriteBatch = new SpriteBatch();
	private final BitmapFont font = new BitmapFont();
	private final OrthographicCamera screenCamera = new OrthographicCamera(0, 0);

	// Setup
	private OrthographicCamera worldCamera;
	private InputProcessor oldInputProcessor;
	private float oldCameraZoom;
	private float oldCameraViewportW;
	private float oldCameraViewportH;
	private Vector3 oldCameraPosition;

	// Selection
	private final List<Sprite> selectedSprites = new ArrayList<Sprite>();
	private Sprite mouseOverSprite;

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void setup(OrthographicCamera worldCamera) {
		this.worldCamera = worldCamera;
		screenCamera.viewportWidth = Gdx.graphics.getWidth();
		screenCamera.viewportHeight = Gdx.graphics.getHeight();
		screenCamera.position.x = screenCamera.viewportWidth/2;
		screenCamera.position.y = screenCamera.viewportHeight/2;
		screenCamera.update();
	}

	// -------------------------------------------------------------------------
	// Editor overrides
	// -------------------------------------------------------------------------
	
	@Override
	public void initialize() {
		registerProperty(Sprite.class, SpriteTweenAccessor.POSITION_XY, "position", new Field("x", 1), new Field("y", 1));
		registerProperty(Sprite.class, SpriteTweenAccessor.ROTATION, "rotation", new Field("rotation", 5));
		registerProperty(Sprite.class, SpriteTweenAccessor.OPACITY, "opacity", new Field("opacity", 0, 1, 0.1f));
		registerProperty(Sprite.class, SpriteTweenAccessor.SCALE_XY, "scale", new Field("scaleX", 0.1f), new Field("scaleY", 0.1f));
	}

	@Override
	public void enable() {
		oldCameraZoom = worldCamera.zoom;
		oldCameraViewportW = worldCamera.viewportWidth;
		oldCameraViewportH = worldCamera.viewportHeight;
		oldCameraPosition = worldCamera.position.cpy();

		oldInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		sprites.clear();
		for (Object target : getAnimationDef().targets)
			if (target instanceof Sprite) sprites.add((Sprite) target);
	}

	@Override
	public void disable() {
		worldCamera.zoom = oldCameraZoom;
		worldCamera.position.set(oldCameraPosition);
		worldCamera.update();

		Gdx.input.setInputProcessor(oldInputProcessor);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.gl10;
		gl.glEnable(GL10.GL_BLEND);

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		for (Sprite sp : selectedSprites)
			drawBoundingBox(worldCamera.combined, gl, sp, SELECTED_SPRITE_BOX_COLOR);
		if (mouseOverSprite != null)
			drawBoundingBox(worldCamera.combined, gl, mouseOverSprite, MOUSEOVER_SPRITE_BOX_COLOR);

		drawRect(screenCamera.combined, 6, 4, w-10, h-10, SHADOW_COLOR);
		drawRect(screenCamera.combined, 5, 5, w-10, h-10, Color.RED);
		drawRect(screenCamera.combined, 13, h-21, 10, 10, SHADOW_COLOR);
		drawRect(screenCamera.combined, 12, h-20, 10, 10, Color.RED);

		int txtY = h + 11;

		spriteBatch.setProjectionMatrix(screenCamera.combined);
		spriteBatch.begin();
		font.setColor(Color.RED);
		font.draw(spriteBatch, "Recording \"" + getAnimationDef().name + "\"", 27, txtY-=20);
		if (!selectedSprites.isEmpty()) {
			String name = selectedSprites.size() == 1
				? getAnimationDef().targetsNamesMap.get(selectedSprites.get(0))
				: "<" + selectedSprites.size() + " sprites>";
			font.setColor(Color.BLUE);
			font.draw(spriteBatch, "Selected: " + name, 10, txtY-=20);
		}
		if (mouseOverSprite != null) {
			String name = getAnimationDef().targetsNamesMap.get(mouseOverSprite);
			font.setColor(Color.BLUE);
			font.draw(spriteBatch, "Mouseover :" + name, 10, txtY-=20);
		}
		spriteBatch.end();

		drawRect(worldCamera.combined,
			oldCameraPosition.x-oldCameraViewportW/2, 
			oldCameraPosition.y-oldCameraViewportH/2,
			oldCameraViewportW, oldCameraViewportH, FRAME_COLOR);
	}

	@Override
	public void selectedObjectsChanged(List<Object> objs) {
		selectedSprites.clear();
		for (Object obj : objs) selectedSprites.add((Sprite) obj);
	}

	@Override
	public void mouseOverObjectChanged(Object obj) {
		mouseOverSprite = (Sprite) obj;
	}

	// -------------------------------------------------------------------------
	// InputProcessor
	// -------------------------------------------------------------------------

	private final InputProcessor inputProcessor = new InputAdapter() {
		private boolean dragged = false;
		private int lastX;
		private int lastY;

		@Override
		public boolean touchMoved(int x, int y) {
			Vector2 p = screenToWorld(new Vector2(x, y));

			mouseOverSprite = null;
			for (Sprite sp : sprites) if (isOver(p, sp)) mouseOverSprite = sp;
			fireMouseOverObjectChanged(mouseOverSprite);

			lastX = x;
			lastY = y;
			return true;
		}

		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			if (mouseOverSprite != null) {
				if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
					if (!selectedSprites.contains(mouseOverSprite)) selectedSprites.add(mouseOverSprite);
					else selectedSprites.remove(mouseOverSprite);
				} else {
					if (!selectedSprites.contains(mouseOverSprite)) {
						selectedSprites.clear();
						selectedSprites.add(mouseOverSprite);
					}
				}
			} else {
				selectedSprites.clear();
			}
			
			fireSelectedObjectsChanged(selectedSprites);

			lastX = x;
			lastY = y;
			return true;
		}

		@Override
		public boolean touchUp(int x, int y, int pointer, int button) {
			if (dragged) {
				beginReport();
				for (Sprite sp : selectedSprites)
					reportStateChanged(sp, Sprite.class, SpriteTweenAccessor.POSITION_XY);
				endReport();
			}
			dragged = false;
			return true;
		}

		@Override
		public boolean touchDragged(int x, int y, int pointer) {
			Vector2 delta = new Vector2(x, y).sub(lastX, lastY);
			for (Sprite sp : selectedSprites)
				apply(sp, SpriteTweenAccessor.POSITION_XY, delta);

			dragged = true;
			lastX = x;
			lastY = y;
			return true;
		}

		@Override
		public boolean scrolled(int amount) {
			worldCamera.zoom *= amount > 0 ? 1.2f : 1/1.2f;
			worldCamera.update();
			return true;
		}
	};

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private void apply(Sprite sp, int tweenType, Vector2 screenDelta) {
		Vector2 worldDelta = screenToWorld(screenDelta).sub(screenToWorld(new Vector2(0, 0)));
		switch (tweenType) {
			case SpriteTweenAccessor.POSITION_XY:
				sp.translate(worldDelta.x, worldDelta.y);
				break;
		}
	}

	private Vector2 screenToWorld(Vector2 v) {
		Vector3 v3 = new Vector3(v.x, v.y, 0);
		worldCamera.unproject(v3);
		return new Vector2(v3.x, v3.y);
	}

	private Rectangle getBoundingBox(Sprite sp) {
		float left = sp.getX() + sp.getOriginX() * (1 - sp.getScaleX());
		float right = sp.getX() + sp.getWidth() + sp.getOriginX() * (sp.getScaleX() - 1);
		float bottom = sp.getY() + sp.getOriginY() * (1 - sp.getScaleY());
		float top = sp.getY() + sp.getHeight() + sp.getOriginY() * (sp.getScaleY() - 1);
		return new Rectangle(left, bottom, right-left, top-bottom);
	}

	private boolean isOver(Vector2 p, Sprite sp) {
		Vector2 orig = new Vector2(sp.getX() + sp.getOriginX(), sp.getY() + sp.getOriginY());
		Vector2 p2 = rotate(p, orig, -sp.getRotation());
		Rectangle bb = getBoundingBox(sp);
		return bb.x <= p2.x && p2.x <= bb.x + bb.width && bb.y <= p2.y && p2.y <= bb.y + bb.height;
	}

	private Vector2 rotate(Vector2 v, Vector2 o, float angleDeg) {
		float cos = MathUtils.cosDeg(angleDeg);
		float sin = MathUtils.sinDeg(angleDeg);
		float x = v.x;
		float y = v.y;
		float newX = cos*(x-o.x) - sin*(y-o.y) + o.x;
		float newY = sin*(x-o.x) + cos*(y-o.y) + o.y;
		return new Vector2(newX, newY);
	}
	
	private void drawBoundingBox(Matrix4 projModelView, GL10 gl, Sprite sp, Color color) {
		gl.glPushMatrix();
		gl.glTranslatef(+sp.getX()+sp.getOriginX(), +sp.getY()+sp.getOriginY(), 0);
		gl.glRotatef(sp.getRotation(), 0, 0, 1);
		gl.glTranslatef(-sp.getX()-sp.getOriginX(), -sp.getY()-sp.getOriginY(), 0);

		Rectangle bb = getBoundingBox(sp);
		drawRect(projModelView, bb.x, bb.y, bb.width, bb.height, color);

		gl.glPopMatrix();
	}

	private void drawRect(Matrix4 projModelView, float x, float y, float w, float h, Color c) {
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glLineWidth(2);
		imr.begin(projModelView, GL10.GL_LINE_LOOP);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x, y, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x, y+h, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x+w, y+h, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x+w, y, 0);
		imr.end();
	}
}
