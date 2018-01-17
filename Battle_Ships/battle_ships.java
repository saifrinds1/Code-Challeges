
//Author Saifudeen Masood.
//github: saifrinds1
//Email: saifrinds1@gmail.com

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class battle_ships {
	
	public static char computerChar = '@';
	public static char playerChar = '#';
	public static char attackChar = 'x';
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = new char[10][10];
		printSea(grid);
		print("\nDEPLOY YOUR SHIPS.");
		deployShips(grid,playerChar);
		print("\nCOMPUTER IS DEPLOYING HIS SHIPS.");
		deployComputerShips(grid,computerChar);
		startBattle(grid,attackChar,computerChar,playerChar);
		
	}
	private static void startBattle(char[][] grid, char attack, char computerChar, char playerChar) {
		print("\n");
		int size = grid.length;
		boolean turn = false;
		int cShips=5,pShips=5;
		for(int c=0; c<size;c++) {
			if(turn) {
				if(cShips==0||pShips==0) 
				{
					if(cShips==0)
						print("GAME OVER.\nPLAYER WINS.");
					else if(pShips==0)
						print("GAME OVER.\nCOMPUTER WINS.");
					return;
				}
				turn=false;
				
				print("YOUR TURN");
				int x=0,y=0;
				do {
					x=0;			
					do{
						x = getInt("X:\n");
					} while(x>=10);
					y=0;
					do{
						y = getInt("Y:\n");//Bug here need to solve it#######################!
					}while(y>=10);		
					if(grid[y][x]!=attack) {
						break;
					}
					else {
						print("Already attacked the cordinate retry.");
					}
				}while(true);
				
				if(grid[y][x]=='\u0000') {
					print("You missed!\n");
					grid[y][x]=attack;
				}
				else if(grid[y][x]==computerChar) {
					cShips--;
					print("Computer ship down!\n");
				}
				else if(grid[y][x]==playerChar) {
					pShips--;
					print("Pity you sunk your own ship.\n");
				}
				grid[y][x] = attack;
				
				updateSea(grid);
				print("\n");
	
			}
			else {
				turn =true;
				
				print("COMPUTER'S TURN");
				sleep(2);
				int x=0,y=0;
				do {
					x=0;			
					do{
						x = new Random().nextInt(10);
					} while(x>=10);
					y=0;
					do{
						y = new Random().nextInt(10);
					}while(y>=10);		
					if(grid[y][x]!=' ') {
						break;
					}
				}while(true);
				
				if(grid[y][x]=='\u0000') {
					print("Computer missed!\n");
				}
				else if(grid[y][x]==playerChar) {
					pShips--;
					print("Player ship down!\n");
				}
				else if(grid[y][x]==computerChar) {
					cShips--;
					print("Computer ship sunked!\n");
				}
				grid[y][x] = ' ';
				
				updateSea(grid);
				print("\n");
			}
		}
	}
	private static void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void deployComputerShips(char[][] grid, char cahar) {
		int size = grid.length;
		int cShips = Integer.valueOf(size/2);
		for(int c = 0; c < cShips; c++) {
			sleep(2);
		do {

			int x=new Random().nextInt(10);			
			int y=new Random().nextInt(10);
			if(checkPosition(grid,y,x)) {
				grid[y][x] = cahar;
				updateSea(grid);
				print("\n");
				break;
			}
			
			updateSea(grid);
		}while(true);
		}	
	 
	}
	private static void deployShips(char[][] grid,char shipChar) {
		int size = grid.length;
		int shipToDeploy=Integer.valueOf(size/2);
		for(int c=0;c<shipToDeploy;c++) {
			print("Where do you want deploy your "+c+" ship? Enter the Cordinate.");
			do {
				
				int x=0;			
				do{
					x = getInt("X:");
				} while(x>=10);
				int y=0;
				do{
					y = getInt("Y:");//Bug here need to solve it#######################!
				}while(y>=10);
				
				if(checkPosition(grid,y,x)) {
					grid[y][x] = shipChar;
					print("\nYour ship is deployed at ("+y+","+x+").");
					updateSea(grid);
					break;
				}
				print("Position already taken.\nRetry.");
				updateSea(grid);
			}while(true);
		}
	}
	private static boolean checkPosition(char[][] grid, int x, int y) {
		if (grid[x][y]=='\u0000') {
			return true;			
		}
		return false;
	}
	private static void printSea(char[][] grid) {
		//Warning print sea will only work if size<10
		print("**** Welcome to Battle Ships game ****\n");
		print("Right now, the sea is empty.\n");
		String xAxis = "";
		for(int c = 0; c<grid.length; c++) {
			xAxis +=c;
		}
		print("   "+xAxis+"   ");
		for(int c = 0; c<grid.length; c++) {
			String row = "";
			for(char cahar :grid[c]) {
				row += cahar;
			}
			print(c+" |"+row+"| "+c);
			row="";
		}
		print("   "+xAxis+"   ");
	}
	private static void updateSea(char[][] grid) {
		String xAxis = "";
		for(int c = 0; c<grid.length; c++) {
			xAxis +=c;
		}
		print("   "+xAxis+"   ");
		for(int c = 0; c<grid.length; c++) {
			String row = "";
			for(char cahar :grid[c]) {
				row += cahar;
			}
			print(c+" |"+row+"| "+c);
			row="";
		}
		print("   "+xAxis+"   ");
	}
	private static int getInt(String s) {
		print(s);
		return new Scanner(System.in).nextInt();
	}
	private static void print(String msg) { System.out.println(msg);}

}