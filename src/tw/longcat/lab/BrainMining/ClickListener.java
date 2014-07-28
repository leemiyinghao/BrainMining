package tw.longcat.lab.BrainMining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener{
    @EventHandler
    public boolean onPlayerInteract(PlayerInteractEntityEvent e){
    	if(!(e.getRightClicked() instanceof Player))
    		return false;
    	if(e.getPlayer().getItemInHand().getType() != Material.WOOD_PICKAXE &&
    	   e.getPlayer().getItemInHand().getType() != Material.STONE_PICKAXE &&
    	   e.getPlayer().getItemInHand().getType() != Material.IRON_PICKAXE &&
    	   e.getPlayer().getItemInHand().getType() != Material.GOLD_PICKAXE &&
    	   e.getPlayer().getItemInHand().getType() != Material.DIAMOND_PICKAXE){
    		return false;
    	}
    	Player targetPlayer = (Player)e.getRightClicked();
		Location targetPlayerLoc = e.getRightClicked().getLocation();
		Location playerLoc = e.getPlayer().getLocation();
		targetPlayerLoc.setY(0);
		playerLoc.setY(0);
		double targetY = targetPlayerLoc.distance(playerLoc)*Math.tan(Math.toRadians(playerLoc.getPitch()*-1)) + e.getPlayer().getEyeHeight() + e.getPlayer().getLocation().getY();
		double targetPlayerEyeY = targetPlayer.getEyeHeight() + targetPlayer.getLocation().getY();
		if(Math.abs(targetY - targetPlayerEyeY) > 0.35){
			return false;
		}
		ItemStack ore = new ItemStack(1);
		Material[] mtArr = {Material.COAL,
							  Material.COAL,
							  Material.COAL,
							  Material.IRON_ORE,
							  Material.IRON_ORE,
							  Material.IRON_ORE,
							  Material.GOLD_ORE,
							  Material.GOLD_ORE,
							  Material.COBBLESTONE,
							  Material.COBBLESTONE,
							  Material.COBBLESTONE,
							  Material.COBBLESTONE,
							  Material.EMERALD};
		ore.setType(mtArr[(int) Math.floor(Math.random()*13)]);
		ore.setAmount((int)Math.floor(Math.random()*8) + 1);
		targetPlayerLoc.setY(targetPlayerEyeY);
		targetPlayerLoc.getWorld().dropItemNaturally(targetPlayerLoc, ore);
		e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() + 5));
    	return true;
    }
}
