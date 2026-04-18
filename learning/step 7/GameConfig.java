package game.test;

public class GameConfig {
		private String userName; //null
		private String computerName = "Компьютер"; //null
		private char userSymbol; //0 или X
		private char computerSymbol; //0 или X
		private boolean userFirst; //false
		
		public GameConfig(String userName, boolean userFirst) {
			this.userFirst = userFirst;
			this.userName = userName;
			this.userSymbol = userFirst ? 'X' : '0';
			this.computerSymbol = userFirst ? '0' : 'X';
		}
		//геттеры
		public String getUserName() {
			return userName;
		}

		public String getComputerName() {
			return computerName;
		}

		public char getUserSymbol() {
			return userSymbol;
		}

		public char getComputerSymbol() {
			return computerSymbol;
		}

		public boolean isUserFirst() {
			return userFirst;
		}

		
		@Override
		public String toString() {
			return ("Игрок: "+this.userName+". Игровой символ: "+this.userSymbol+". "+(this.userFirst?"Ходит первым" : "Ходит вторым"));
		}	
}
