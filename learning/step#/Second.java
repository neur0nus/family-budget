package fb2.test;

import java.util.Scanner;

public class Second {
	
	public static void main(String[] args) {
		System.out.println("Привет! Я загадал число от 0 до 127. Попробуй угадать!");
		int guessNum = new java.util.Random(System.currentTimeMillis()).nextInt(127);
		int cycleCount = 0;
		
		for (;;) {
			
			cycleCount = cycleCount+1;
			Scanner scanner = new Scanner(System.in);
			System.out.print("Введите ваше предположение: "); // ввод числа пользователем
			int intNum = scanner.nextInt();
			
			if (intNum == guessNum && cycleCount <= 127) {
				
				System.out.println("Поздравляю! Вы угадали число "+guessNum+" за "+cycleCount+" попыток");	// вывод итогового результата
				break;
				
			}// проверка соответствия загаданного числа и пользовательского
			else if (cycleCount > 127){ 
				
				System.out.println("К сожалению вы вышли за предел количества попыток - "+cycleCount);	// конец из-за ограничения числа попыток
				break;
				
			} else {
				
				if (intNum > guessNum){
					
					System.out.println("Загаданное число меньше, чем "+intNum);
					
				} else {
					
					System.out.println("Загаданное число больше, чем "+intNum);
					
				}
				
			}
			
		}
		
	}


}
