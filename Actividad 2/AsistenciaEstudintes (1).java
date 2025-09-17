 
            package A1;
            
            import java.util.Scanner;

            public class AsistenciaEstudintes {
                public static void main(String[] args) {
                    final int DIAS_SEMANA = 5;
                    final int NUM_ESTUDIANTES = 4;

                    String[][] asistencia = new String[NUM_ESTUDIANTES][DIAS_SEMANA];
                    Scanner sc = new Scanner(System.in);
                    int opcion;

                    do {
                        System.out.println("MENU");
                        System.out.println("1. Ver asistencia individual");
                        System.out.println("2. Ver resumen general");
                        System.out.println("3. Volver a registrar");
                        System.out.println("4. Salir");
                        System.out.print("Selecciona una opcion: ");
                        opcion = sc.nextInt();

                        if (opcion == 1) {
                            System.out.println("Asistencia individual:");
                            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                System.out.print("Estudiante " + (i + 1) + ": ");
                                for (int j = 0; j < DIAS_SEMANA; j++) {
                                    System.out.print(asistencia[i][j] + " ");
                                }
                                System.out.println();
                            }
                        }

                        if (opcion == 2) {
                            System.out.println("Total de asistencias por estudiante:");
                            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                int contador = 0;
                                for (int j = 0; j < DIAS_SEMANA; j++) {
                                    if ("P".equals(asistencia[i][j])) {
                                        contador++;
                                    }
                                }
                                System.out.println("Estudiante " + (i + 1) + ": " + contador + " asistencias");
                            }
                                                       
           
                            System.out.println("Estudiantes que asistieron todos los dias:");
                            int encontrados = 0;
                            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                int presentes = 0;
                                for (int j = 0; j < DIAS_SEMANA; j++) {
                                    if ("P".equals(asistencia[i][j])) {
                                        presentes++;
                                    }
                                }
                                if (presentes == DIAS_SEMANA) {
                                    System.out.println("Estudiante " + (i + 1));
                                    encontrados++;
                                }
                            }
                            if (encontrados == 0) {
                                System.out.println("Ninguno");
                            }

                            int maxAusencias = 0;
                            for (int j = 0; j < DIAS_SEMANA; j++) {
                                int ausencias = 0;
                                for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                    if ("A".equals(asistencia[i][j])) {
                                        ausencias++;
                                    }
                                }
                                if (ausencias > maxAusencias) {
                                    maxAusencias = ausencias;
                                }
                            }

                            System.out.println("Dias con mas ausencias:");
                            for (int j = 0; j < DIAS_SEMANA; j++) {
                                int ausencias = 0;
                                for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                    if ("A".equals(asistencia[i][j])) {
                                        ausencias++;
                                    }
                                }
                                if (ausencias == maxAusencias) {
                                    System.out.println("Día " + (j + 1) + ": " + ausencias + " ausencias");
                                }
                            }
                        }

                        if (opcion == 3) {
                            System.out.println("Registro de asistencia:");
                            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                System.out.println("Estudiante " + (i + 1));
                                for (int j = 0; j < DIAS_SEMANA; j++) {
                                    String valor = "";
                                    do {
                                        System.out.print("  Día " + (j + 1) + " (P/A): ");
                                        valor = sc.next().toUpperCase();
                                    } while (!valor.equals("P") && !valor.equals("A"));
                                    asistencia[i][j] = valor;
                                }
                            }
                        }

                    } while (opcion != 4);

                    System.out.println("Programa finalizado.");
                    sc.close();
                }
            }
