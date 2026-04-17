package ticTacToe;

import java.util.Scanner;

public class ticTacToe {
	
	static String cell1 = "";                                        // Перечисление клеток, для записи ходов
	static String cell2 = "";
	static String cell3 = "";
	static String cell4 = "";
	static String cell5 = "";
	static String cell6 = "";
	static String cell7 = "";
	static String cell8 = "";
	static String cell9 = "";
	
	
	
	
	
	public static void printField() {
		
		System.out.println("---------------");
		System.out.println("| "+cell1+" || "+cell2+" || "+cell3+" |");
		System.out.println("---------------");
		System.out.println("| "+cell4+" || "+cell5+" || "+cell6+" |");
		System.out.println("---------------");
		System.out.println("| "+cell7+" || "+cell8+" || "+cell9+" |");
		System.out.println("---------------");
		System.out.println("");
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Привет! Поиграем в крестики нолики?");
		System.out.println("Ты ходишь крестиками.");
		System.out.println("Поле игры представляет собой 9 клеток, размеченных номерами:");
		System.out.println("---------------");
		System.out.println("| 1 || 2 || 3 |");
		System.out.println("---------------");
		System.out.println("---------------");
		System.out.println("| 4 || 5 || 6 |");
		System.out.println("---------------");
		System.out.println("---------------");
		System.out.println("| 7 || 8 || 9 |");
		System.out.println("---------------");
		System.out.println("");
		System.out.println("Для совершения хода напиши номер выбранной клетки");
		
		
		int turnCount = 0;                                                         // Счетчик ходов
		
		for (;;) {
			
			printField();
			
			if (turnCount == 9) {                                                  // Проверка по счетчику ходов на ничью
				
				System.out.println("Похоже, что получилась ничья!");
				break;
					
			} else if(turnCount % 2 == 0) {                                        // Ход пользователя по счетчику
				
				turnCount = turnCount+1;
				Scanner scanner = new Scanner(System.in);
				System.out.print("Твой ход. Напиши номер клетки : ");
				int userMove = scanner.nextInt();
				
				if (userMove == 1 && cell1 == "") {
					
					cell1 = "X";
					
				} else if (userMove == 2 && cell2 == "") {
					
					cell2 = "X";
					
				} else if (userMove == 3 && cell3 == "") {
					
					cell3 = "X";

					
				} else if (userMove == 4 && cell4 == "") {
					
					cell4 = "X";
					
				} else if (userMove == 5 && cell5 == "") {
					
					cell5 = "X";
					
				} else if (userMove == 6 && cell6 == "") {
					
					cell6 = "X";
					
				} else if (userMove == 7 && cell7 == "") {
					
					cell7 = "X";
					
				} else if (userMove == 8 && cell8 == "") {
					
					cell8 = "X";
					
				} else if (userMove == 9 && cell9 == "") {
					
					cell9 = "X";
			
					
			    } else {
					
					System.out.println("Ошибка! Клетка может быть занята или ты написал цифру, не входящуюю в пределы поля.");
					System.out.println("Попробуй снова!");
					
				}
				
				
			} else if(turnCount % 2 != 0) {                                                                                // Ход компьютера по счетчику
				
				turnCount = turnCount+1;
				System.out.println("Ход компьютера!");
				System.out.println("");
				System.out.println("...");
				System.out.println("");
				
				// Проверка возможных выйгрышных ходов компьютера
				// Проверка горизонтальных линий
				// Проверка строчки 1-2-3
					
					if (cell1 == "0" && cell2 == "0" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell2 == "0" && cell3 == "0" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "0" && cell3 == "0" && cell2 == "") {
						
						cell2 = "0";
						System.out.println("Я сходил!");
						
						// Проверка строчки 4-5-6
					} else if (cell4 == "0" && cell5 == "0" || cell5 == "0" && cell6 == "0" || cell4 == "0" && cell6 == "0") {
					
					if (cell4 == "0" && cell5 == "0" && cell6 == "") {
						
						cell6 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "0" && cell6 == "0" && cell4 == "") {
						
						cell4 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell4 == "0" && cell6 == "0" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						
					}
					
					// Проверка строчки 7-8-9
				    } else if (cell7 == "0" && cell8 == "0" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell8 == "0" && cell9 == "0" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell7 == "0" && cell9 == "0" && cell8 == "") {
						
						cell8 = "0";
						System.out.println("Я сходил!");
					
					// Проверка вертикальных линий
					// Проверка строчки 1-4-7
				    } else if (cell1 == "0" && cell4 == "0" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell4 == "0" && cell7 == "0" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "0" && cell7 == "0" && cell4 == "") {
						
						cell4 = "0";
						System.out.println("Я сходил!");
				
					// Проверка строчки 2-5-8
				   } else if (cell2 == "0" && cell5 == "0" && cell8 == "") {
						
						cell8 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "0" && cell8 == "0" && cell2 == "") {
						
						cell2 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell2 == "0" && cell8 == "0" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						
					
					// Проверка строчки 3-6-9
				    } else if (cell3 == "0" && cell6 == "0" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell6 == "0" && cell9 == "0" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell3 == "0" && cell9 == "0" && cell6 == "") {
						
						cell6 = "0";
						System.out.println("Я сходил!");

					
					// Проверка диагональных линий
					// Проверка строчки 1-5-9
			    	} else if (cell1 == "0" && cell5 == "0" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "0" && cell9 == "0" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "0" && cell9 == "0" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						
				
					// Проверка строчки 7-5-3
				    } else if (cell7 == "0" && cell5 == "0" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "0" && cell3 == "0" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell7 == "0" && cell3 == "0" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						
					
				    // Проверка на выйгрышные ходы игрока, которые можно заблокировать
					// Проверка горизонтальных линий
					// Проверка строчки 1-2-3
				    } else if (cell1 == "X" && cell2 == "X" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell2 == "X" && cell3 == "X" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "X" && cell3 == "X" && cell2 == "") {
						
						cell2 = "0";
						System.out.println("Я сходил!");
						

					// Проверка строчки 4-5-6
				    } else if (cell4 == "X" && cell5 == "X" && cell6 == "") {
						
						cell6 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "X" && cell6 == "X" && cell4 == "") {
						
						cell4 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell4 == "X" && cell6 == "X" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						
					
					// Проверка строчки 7-8-9
				    } else if (cell7 == "X" && cell8 == "X" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell8 == "X" && cell9 == "X" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell7 == "X" && cell9 == "X" && cell8 == "") {
						
						cell8 = "0";
						System.out.println("Я сходил!");

					
					// Проверка вертикальных линий
					// Проверка строчки 1-4-7
				    } else if (cell1 == "X" && cell4 == "X" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell4 == "X" && cell7 == "X" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "X" && cell7 == "X" && cell4 == "") {
						
						cell4 = "0";
						System.out.println("Я сходил!");

				
					// Проверка строчки 2-5-8
				    } else if (cell2 == "X" && cell5 == "X" && cell8 == "") {
						
						cell8 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "X" && cell8 == "X" && cell2 == "") {
						
						cell2 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell2 == "X" && cell8 == "X" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");

					
					// Проверка строчки 3-6-9
				    } else if (cell3 == "X" && cell6 == "X" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell6 == "X" && cell9 == "X" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell3 == "X" && cell9 == "X" && cell6 == "") {
						
						cell6 = "0";
						System.out.println("Я сходил!");

					
					// Проверка диагональных линий
					// Проверка строчки 1-5-9
				    } else if (cell1 == "X" && cell5 == "X" && cell9 == "") {
						
						cell9 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "X" && cell9 == "X" && cell1 == "") {
						
						cell1 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell1 == "X" && cell9 == "X" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");

				
					// Проверка строчки 7-5-3
				    } else if (cell7 == "X" && cell5 == "X" && cell3 == "") {
						
						cell3 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell5 == "X" && cell3 == "X" && cell7 == "") {
						
						cell7 = "0";
						System.out.println("Я сходил!");
						
					} else if (cell7 == "X" && cell3 == "X" && cell5 == "") {
						
						cell5 = "0";
						System.out.println("Я сходил!");
						

					// Если в предыдущих вариантах ничего нет - ставим 0 в центр
				    } else if (cell5 == "") {
					
					cell5 = "0";
					System.out.println("Я сходил!");
					
					// Если центр занят - ставим по углам
				    } else if (cell1 == "") {
					
					cell1 = "0";
					System.out.println("Я сходил!");
					
				    } else if (cell3 == "") {
					
					cell3 = "0";
					System.out.println("Я сходил!");
					
				    } else if (cell7 == "") {
					
					cell7 = "0";
					System.out.println("Я сходил!");
					
				    } else if (cell9 == "") {
					
					cell9 = "0";
					System.out.println("Я сходил!");
					
					// Если заняты углы - ставим в оставшееся свободное место
				    } else if (cell2 == "") {
					
					cell2 = "0";
					System.out.println("Я сходил!");
					
				    } else if (cell4 == "") {
					
					cell4 = "0";
					System.out.println("Я сходил!");
					
				    } else if (cell6 == "") {
					
					cell6 = "0";
					System.out.println("Я сходил!");
					
				   } else if (cell8 == "") {
					
					cell8 = "0";
					System.out.println("Я сходил!");
				
				
					// Проверка выйгрышных комбинаций пользователя
			    } else if (cell1 == "X" && cell2 == "X" && cell3 == "X" || cell4 == "X" && cell5 == "X" && cell6 == "X" || cell7 == "X" && cell8 == "X" && cell9 == "X" || cell1 == "X" && cell5 == "X" && cell9 == "X" || cell3 == "X" && cell5 == "X" && cell7 == "X") {
				
				System.out.println("Вы победили тупую машину, поздравляю!");
				printField();
				break;
				
				// Проверка выйгрышных комбинаций компьютера
				
			} else if (cell1 == "0" && cell2 == "0" && cell3 == "0" || cell4 == "0" && cell5 == "0" && cell6 == "0" || cell7 == "0" && cell8 == "0" && cell9 == "0" || cell1 == "0" && cell5 == "0" && cell9 == "0" || cell3 == "0" && cell5 == "0" && cell7 == "0") {
				
				System.out.println("Тупая машина победила!");
				printField();
				break;
				
			}
			
			
		}

		
		
	}
	

}}
