package com.gamepad.lib.andengine.entity.scene.background;

import com.gamepad.lib.andengine.engine.handler.IDrawHandler;
import com.gamepad.lib.andengine.engine.handler.IUpdateHandler;
import com.gamepad.lib.andengine.util.color.Color;
import com.gamepad.lib.andengine.util.modifier.IModifier;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:47:41 - 19.07.2010
 */
public interface IBackground extends IDrawHandler, IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void registerBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
	public boolean unregisterBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
	public void clearBackgroundModifiers();

	public boolean isColorEnabled();
	public void setColorEnabled(final boolean pColorEnabled);

	public void setColor(final Color pColor);
	public void setColor(final float pRed, final float pGreen, final float pBlue);
	public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha);
}