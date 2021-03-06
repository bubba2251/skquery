package com.w00tmast3r.skquery.elements.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.w00tmast3r.skquery.api.Description;
import com.w00tmast3r.skquery.api.Examples;
import com.w00tmast3r.skquery.api.Name;
import com.w00tmast3r.skquery.api.Patterns;
import com.w00tmast3r.skquery.util.Collect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Client Sign")
@Description("Cause a sign to have custom lines to certain viewers")
@Examples("command /hidesign:;->trigger:;->->make all players see lines of targeted block as \"\", \"\", \"\", \"\"")
@Patterns("make %players% see lines of %block% as %string%, %string%, %string%[ and], %string%")
public class EffClientSign extends Effect {

    private Expression<Player> player;
    private Expression<Block> block;
    private Expression<String> l1, l2, l3, l4;

    @Override
    protected void execute(Event event) {
        Block b = block.getSingle(event);
        String a1 = l1.getSingle(event);
        String a2 = l2.getSingle(event);
        String a3 = l3.getSingle(event);
        String a4 = l4.getSingle(event);
        if(b == null || (b.getType() != Material.SIGN_POST || b.getType() != Material.WALL_SIGN)) return;
        for(Player p : player.getAll(event)){
            p.sendSignChange(b.getLocation(), Collect.asArray(a1, a2, a3, a4));
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "credits";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        block = (Expression<Block>) expressions[1];
        l1 = (Expression<String>) expressions[2];
        l2 = (Expression<String>) expressions[3];
        l3 = (Expression<String>) expressions[4];
        l4 = (Expression<String>) expressions[5];
        return true;
    }
}
