import java.awt.*;

//设计窗口布局
public class GameUtil {
    static int MAP_W=36;//地图的宽
    static int MAP_H=36;//地图的高
    static int OFFSET=45;//雷区偏移量
    static int SQUARE_LENGTH=45;//格子边长
    static int Lei_MAX=100;//地雷个数
    static int FLAG_NUM=0;//插旗数量
    //鼠标相关
    static int Mouse_X;//横坐标
    static int Mouse_Y;//纵坐标
    //状态
    static boolean LEFT=false;
    static  boolean RIGHT=false;
    //游戏状态 0 游戏中  1 胜利  2失败 3 难度选择
    static int state=3;
    //游戏难度
    static int level;

    //倒计时
    static long Start_TIME;
    static long End_TIME;

    //底层元素：-1 雷 0 空 1-8 对应数字
    static int[][] DATA_BOTTON=new int[MAP_W+2][MAP_H+2];
    //顶层元素：-1 无覆盖区 0 覆盖区 1 插旗区 2 插错旗
    static int[][] DATA_TOP=new int[MAP_W+2][MAP_H+2];
    //载入图片
    static Image lei=Toolkit.getDefaultToolkit().getImage("pic/EXO.jpg");
    static Image top=Toolkit.getDefaultToolkit().getImage("pic/Grayglass.jpg");
    static Image flag=Toolkit.getDefaultToolkit().getImage("pic/xx.jpg");
    static Image noflag=Toolkit.getDefaultToolkit().getImage("pic/Boom.jpg");
    static Image ing=Toolkit.getDefaultToolkit().getImage("pic/ing.jpg");
    static Image over=Toolkit.getDefaultToolkit().getImage("pic/over.jpg");
    static Image win=Toolkit.getDefaultToolkit().getImage("pic/win.jpg");
    static Image[] images=new Image[9];
    static {
        for(int i=1;i<=8;i++)
        {
            images[i]=Toolkit.getDefaultToolkit().getImage("pic/"+i+".jpg");
        }
    }

    static void drawWord(Graphics g,String str,int x,int y,int size,Color color){
        g.setColor(color);
        g.setFont(new Font("宋体",Font.BOLD,30));
        g.drawString(str,x,y);
    }
}