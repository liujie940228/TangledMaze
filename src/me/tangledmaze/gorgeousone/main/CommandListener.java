package me.tangledmaze.gorgeousone.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.tangledmaze.gorgeousone.commands.*;

public class CommandListener implements CommandExecutor {
	
	private Select selectCommand;
	private Start startCommand;
	private Add addCommand;
	private Subtract subtCommand;
	private Deselect deselectCommand;
	private Build buildCommand;
	
	public CommandListener() {
		selectCommand = new Select();
		startCommand = new Start();
		addCommand = new Add();
		subtCommand = new Subtract();
		deselectCommand = new Deselect();
		buildCommand = new Build();
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String name, String[] args) {
		
		if (commandSender instanceof ConsoleCommandSender)
			return true;
		
		Player p = (Player) commandSender;
		
		if (!command.getName().equalsIgnoreCase("tangledmaze"))
			return true;
		
		if (args.length < 1) {
			sendHelp(p, 0);
			return false;
		}
		
		switch (args[0].toLowerCase()) {
			case "wand":
				p.getInventory().addItem(TangledMain.getWand());
				break;
				
			case "select":
				if(args.length >= 2)
					selectCommand.execute(p, args[1]);
				else
					sendHelp(p, 2);
				break;
				
			case "start":
				startCommand.execute(p);
				break;
				
			case "add":
			case "merge":
				addCommand.execute(p);
				break;
				
			case "cut":
			case "subtract":
				subtCommand.execute(p);
				break;
				
			case "deselect":
				deselectCommand.execute(p);
				break;

			case "build":
				buildCommand.execute(p);
				break;
			
			case "undo":
				break;
				
			case "help":
			case "h":
			case "?":
			default:
				if (args.length > 2) {
					try {
						int page = Integer.parseInt(args[2]);
						sendHelp(p, page);
					} catch (NumberFormatException e) {
						sendHelp(p, 1);
						break;
					}
				}
				sendHelp(p, 1);
				break;
		}
			
		return true;
	}
	
	
//	private void hasAnyPermission(Permission... perms) {
//	}
//	
//	private void hasAllPermissions(Permission... perms) {
//	}
	
	private void sendHelp(Player p, int page) {
		p.sendMessage("Help");
	}

}
