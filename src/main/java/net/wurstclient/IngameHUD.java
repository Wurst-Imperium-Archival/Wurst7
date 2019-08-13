/*
 * Copyright (C) 2014 - 2019 | Wurst-Imperium | All rights reserved.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient;

import org.lwjgl.opengl.GL11;

import net.wurstclient.clickgui.ClickGui;
import net.wurstclient.clickgui.ClickGuiScreen;
import net.wurstclient.events.GUIRenderListener;

public final class IngameHUD implements GUIRenderListener
{
	private final WurstLogo wurstLogo = new WurstLogo();
	private final ModList modList = new ModList();
	// private static final TabGui tabGui = new TabGui();
	
	@Override
	public void onRenderGUI(float partialTicks)
	{
		if(!WurstClient.INSTANCE.isEnabled())
			return;
		
		boolean blend = GL11.glGetBoolean(GL11.GL_BLEND);
		ClickGui clickGui = WurstClient.INSTANCE.getGui();
		
		// GL settings
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		clickGui.updateColors();
		
		wurstLogo.render();
		modList.render(partialTicks);
		// tabGui.render(partialTicks);
		
		// pinned windows
		if(!(WurstClient.MC.currentScreen instanceof ClickGuiScreen))
			clickGui.renderPinnedWindows(partialTicks);
		
		// GL resets
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1, 1, 1, 1);
		
		if(blend)
			GL11.glEnable(GL11.GL_BLEND);
		else
			GL11.glDisable(GL11.GL_BLEND);
	}
	
	public ModList getHackList()
	{
		return modList;
	}
}
