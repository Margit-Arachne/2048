import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    int[][] data = {
            {0, 2, 2, 4},
            {2, 2, 4, 4},
            {0, 8, 2, 4},
            {0, 32, 0, 64}
    };

    public MainFrame() {
        initFrame();
        paintView();
        this.addKeyListener(this);
        setVisible(true);
    }

    public void initFrame(){
        setSize(514, 538);
        setAlwaysOnTop(true);
        setTitle("2048");
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗体居中
        setLocationRelativeTo(null);
        //取消默认布局
        setLayout(null);

    }

    public void paintView(){
        getContentPane().removeAll();
        for(int i=0; i<data.length; i++){
            for(int j=0; j<data[i].length; j++){
                JLabel imgLabel = new JLabel(new ImageIcon(Main.class.getResource("/images/img-"+data[i][j]+".png")));
                imgLabel.setBounds(50+j*100, 50+i*100, 100, 100);
                getContentPane().add(imgLabel);
            }
        }
        JLabel background = new JLabel(new ImageIcon(Main.class.getResource("/images/img-background.png")));
        background.setBounds(40, 40, 420, 420);
        getContentPane().add(background);
        getContentPane().repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if(KeyCode == 37){//     左
            moveLeft(data);
        }else if(KeyCode == 38){//  上
            moveUp(data);
        }else if(KeyCode == 39){//   右
            moveRight(data);
        }else if(KeyCode == 40){//   下
            moveDown(data);
        }
        paintView();
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

    public void moveRight(int[][] data){
        moveReverse(data);
        moveLeft(data);
        moveReverse(data);
    }

    public void moveReverse(int[][] data){
        for(int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length / 2; j++) {
                // 交换每行左右对称的元素
                int temp = data[i][j];
                data[i][j] = data[i][data[i].length - j - 1];
                data[i][data[i].length - j - 1] = temp;
            }
        }
    }

    public void moveUp(int[][] data){
        moveAntiClockWise(data);
        moveLeft(data);
        moveClockWise(data);
    }

    public void moveDown(int[][] data){
        moveClockWise(data);
        moveLeft(data);
        moveAntiClockWise(data);
    }

    public void moveClockWise(int[][] data){
        int [][] newData = new int[data.length][data[0].length];
        for(int i=0; i<data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                newData[j][data.length - i - 1] = data[i][j];
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(newData[i], 0, data[i], 0, data[i].length);
        }
    }

    public void moveAntiClockWise(int[][] data){
        int [][] newData = new int[data.length][data[0].length];
        for(int i=0; i<data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                newData[data.length - j - 1][i] = data[i][j];
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(newData[i], 0, data[i], 0, data[i].length);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
