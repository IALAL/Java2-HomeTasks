package Hometask_2;

public class Main {

    private static final class  RowNoMatchEx extends RuntimeException{
        RowNoMatchEx(String msg){super("Rows exeption: " + msg);}
    }
    private static final class  ColNoMatchEx extends RuntimeException{
        ColNoMatchEx(String msg){super("Columns exeption: " + msg);}
    }
    private static final class  NotNumberEx extends RuntimeException{
        NotNumberEx(String msg){super("Not a number found: " + msg);}
    }

    private static String CORRECT_STR = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static String EX_COL_STR = "10 3 1 3 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static String EX_ROW_STR = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\n1 3 4 5";
    private static String NO_ROW_STR = "10 3 1 2\n2 3 2 2\n300 3 1 0";
    private static String NO_COL_STR = "10 3 1 \n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static String CHAR_STR = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 B 1 0";

    private static final int MATRIX_ROWS = 4;
    private static final int MATRIX_COLS = 4;

    public static void main(String[] args) {
        System.out.println(MathMatrix(STRtoMATRIX(CORRECT_STR)));
//       MathMatrix(STRtoMATRIX(EX_COL_STR));
//       MathMatrix(STRtoMATRIX(EX_ROW_STR));
//       MathMatrix(STRtoMATRIX(NO_ROW_STR));
//       MathMatrix(STRtoMATRIX(NO_COL_STR));
//       MathMatrix(STRtoMATRIX(CHAR_STR));

    }

    private static String[][] STRtoMATRIX(String str){
        String[] rows=str.split("\n");
        if(rows.length != MATRIX_ROWS) {
            throw new RowNoMatchEx(rows.length+":\n"+str);
        }
        String[][] result = new String[MATRIX_ROWS][];
        for (int i = 0; i < MATRIX_ROWS ; i++) {
                result[i] = rows[i].split(" ");
                if(result[i].length != MATRIX_COLS)
                    throw new ColNoMatchEx(result[i].length+":\n"+str);
            }
        return result;
    }

    private static float MathMatrix(String[][] matrix){
        int result = 0;
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                try {
                    result += Integer.parseInt(matrix[i][j]);
                }catch (NumberFormatException e){
                    throw new NotNumberEx(matrix[i][j]);
                }

            }
        }
        return result / 2;
    }
}
