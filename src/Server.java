import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.LinkedList;

public class Server {
	
	static int players = 0;
	static LinkedList<Game> games = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket socket;
		
		try {

			socket = new ServerSocket(5000);
			games = new LinkedList<Game>();
			
			while(true) {
				if (players == 1)
					System.out.println("There is 1 player online.");
				else
					System.out.println("There are " + players + " players online.");
				Socket client = socket.accept();
				if (players % 2 == 1) {
					Game game = games.getLast();
					game.addPlayer(client);
					game.notify();
				}
				else {
					Game game = new Game(client);
					game.run();
					games.add(game);
				}
				++players;
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}
}
