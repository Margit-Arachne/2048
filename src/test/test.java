package test;

public class test {

        public static void main(String[] args) {
            int[][] data = {
                    {0, 2, 2, 4},
                    {2, 2, 4, 4},
                    {0, 8, 2, 4},
                    {0, 32, 0, 64}
            };
            test hello = new test();
            hello.checkLeft(data);
        }
        public void moveLeft(int[][] data){
            for(int i=0; i<data.length; i++){
                int[] newRow = new int[data[i].length];
                int index = 0;
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] != 0) {
                        newRow[index] = data[i][j];
                        index++;
                    }
                }
                data[i] = newRow;
                for(int j=0; j < data.length - 1; j++){
                    if (data[i][j] == data[i][j+1]) {
                        data[i][j] *= 2;
                        for (int k = j + 1; k < data.length - 1; k++)
                            data[i][k] = data[i][k + 1];
                        data[i][data.length - 1] = 0;
                    }
                }
            }
        }

        public void show(int[][] data){
            for (int[] datum : data) {
                for (int i : datum) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public boolean checkLeft(int[][] data){
            int[][] newdata = new int[data.length][data[0].length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    newdata[i][j] = data[i][j];
                }
            }
            show(newdata);
            moveLeft(newdata);
            show(newdata);
            show(data);
            for(int i=0; i<data.length; i++){
                for(int j=0; j<data[i].length; j++){
                    if(data[i][j] != data[i][j]){
                        return true;
                    }
                }
            }
            return false;
        }
}
