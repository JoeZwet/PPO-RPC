/*
 * Copyright (c) 2019 Joe van der Zwet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.joezwet.galacticraft.rpc.discord;

import com.sun.scenario.effect.Offset;
import me.joezwet.galacticraft.rpc.RPC;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.time.OffsetDateTime;

public class DiscordDimSwitcher {

    public static void switchDim(int dim) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();
        Dimension dimension = RPC.instance.dimensionInfo.getDimensionInfo(dim);

        if(dimension == null) {
            presence.state = "Exploring";
            presence.largeImageText = "";
            presence.largeImageKey = "";
            presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
            RPC.instance.discord.setPresence(presence);
            return;
        }

        presence.state = dimension.getState();
        presence.largeImageKey = dimension.getLargeImageKey();
        presence.largeImageText = dimension.getLargeImageText();

        presence.startTimestamp = OffsetDateTime.now().toEpochSecond();
        RPC.instance.discord.setPresence(presence);
    }

    public static void switchServer(int server) {

        DiscordRichPresence presence = RPC.instance.discord.getPresence();

        switch (server) {
            case 0:
                presence.details = "Playing Singleplayer";
                break;
            case 1:
                presence.details = "Playing Multiplayer";
                break;
        }

        RPC.instance.discord.setPresence(presence);
    }
}
