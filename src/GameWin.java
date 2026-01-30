import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    Image offScreemImage=null;//底层画布
    MapBotton mapBotton=new MapBotton();
    MapTop mapTop=new MapTop();
    GameSelect gameSelect=new GameSelect();
    //是否开始 f 未开始 t 开始
    boolean begin=false;
    int width=2*GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH;
    int height=4*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH;
    public void launch() {
        //游戏开始记录时间
        GameUtil.Start_TIME=System.currentTimeMillis();
        //设置窗口可见
        this.setVisible(true);
        //设置窗口大小
        if(GameUtil.state==3){
            this.setSize(500,500);
        }
        else{
            this.setSize(width,height);
        }
        //this.setSize(width,height);
        //设置窗口位置
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("扫雷游戏");
        //关闭窗口
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state){
                    case 0:
                        if(e.getButton()==1)//左键
                        {
                            //System.out.println(1);
                            GameUtil.Mouse_X=e.getX();
                            GameUtil.Mouse_Y=e.getY();
                            GameUtil.LEFT=true;
                        }
                        if(e.getButton()==3)//右键
                        {
                            //System.out.println(3);
                            GameUtil.Mouse_X=e.getX();
                            GameUtil.Mouse_Y=e.getY();
                            GameUtil.RIGHT=true;
                        }

                    case 1:

                    case 2:
                        if(e.getButton()==1){
                            if(e.getX()>GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2) &&
                                    e.getX()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2)+GameUtil.SQUARE_LENGTH &&
                                    e.getY()>GameUtil.OFFSET&&e.getY()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH){
                                mapBotton.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM=0;
                                GameUtil.Start_TIME=System.currentTimeMillis();
                                GameUtil.state=0;
                            }
                        }
                        if(e.getButton()==2){
                            GameUtil.state=3;
                            begin=true;
                        }
                        break;
                    case 3:
                        if(e.getButton()==1){
                            GameUtil.Mouse_X=e.getX();
                            GameUtil.Mouse_Y=e.getY();
                            begin=gameSelect.hard();
                        }
                        break;
                    default:
                }


            }
        });

        while(true)
        {
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void begin(){
        if(begin){
            begin=false;
            gameSelect.hard(GameUtil.level);
            dispose();
            GameWin gameWin=new GameWin();
            GameUtil.Start_TIME=System.currentTimeMillis();
            GameUtil.FLAG_NUM=0;
            mapBotton.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    public void paint(Graphics gra){
        if(GameUtil.state==3){
            //gra.setColor(Color.white);
            //gra.fillRect(0,0,500,500);
            gameSelect.paintSelf(gra);
        }
        else {
            offScreemImage = this.createImage(width, height);
            Graphics gImage = offScreemImage.getGraphics();
            //设置背景颜色
            gImage.setColor(Color.black);
            gImage.fillRect(0, 0, width, height);
            mapBotton.painSelf(gImage);
            mapTop.painSelf(gImage);
            gra.drawImage(offScreemImage, 0, 0, null);
        }
    }



    public static void main(String[] args){
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }
}
