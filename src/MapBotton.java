import javax.swing.text.StyledEditorKit;
import java.awt.*;

public class MapBotton {
    BottomLei bottomLei=new BottomLei();
    BottomNum bottomNum=new BottomNum();
    {
        bottomLei.newLei();
        bottomNum.newNum();
    }

    //重置游戏
    void reGame(){
        for(int i=1;i<=GameUtil.MAP_W;i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.DATA_BOTTON[i][j]=0;
            }
        }
        bottomLei.newLei();
        bottomNum.newNum();
    }

    //绘制方法
    public void painSelf(Graphics g){
        //for(int i=0;i<500;i+=50)
        //{
        //    g.drawLine(0,i,500,i);
        //    g.drawLine(i,0,i,500);
        //}
        g.setColor(Color.white);
        //竖线
        for(int i=0;i<= GameUtil.MAP_W;i++)
        {
            g.drawLine(GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                    3*GameUtil.OFFSET,
                    GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                    3*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH);
        }
        //横线
        for(int i=0;i<=GameUtil.MAP_H;i++)
        {
            g.drawLine(GameUtil.OFFSET,
                    3*GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                    GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.OFFSET,
                    3*GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH);
        }

        for(int i=1;i<=GameUtil.MAP_W;i++)
        {
            for(int j=1;j<=GameUtil.MAP_H;j++)
            {
                //雷
                if(GameUtil.DATA_BOTTON[i][j]==-1) {
                    //画图
                    g.drawImage(GameUtil.lei,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            null);
                }
                //数字
                if(GameUtil.DATA_BOTTON[i][j]>=0) {
                    //画图
                    g.drawImage(GameUtil.images[GameUtil.DATA_BOTTON[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            null);
                }
            }
        }

        //绘制数字 剩余雷数
        GameUtil.drawWord(g,""+(GameUtil.Lei_MAX-GameUtil.FLAG_NUM),
                GameUtil.OFFSET,2*GameUtil.OFFSET,30,Color.red);
        GameUtil.drawWord(g,""+(GameUtil.End_TIME-GameUtil.Start_TIME)/1000,//毫秒换算成秒,
                GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH-1,2*GameUtil.OFFSET,30,Color.red);
        //g.setColor(Color.red);
        //g.setFont(new Font("宋体",Font.BOLD,30));
        //g.drawString(""+(GameUtil.Lei_MAX-GameUtil.FLAG_NUM),GameUtil.OFFSET,2*GameUtil.OFFSET);

        switch (GameUtil.state){
                //游戏中
            case 0:
                GameUtil.End_TIME=System.currentTimeMillis();
                g.drawImage(GameUtil.ing,
                        GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                        GameUtil.OFFSET,GameUtil.SQUARE_LENGTH ,GameUtil.SQUARE_LENGTH ,null);
                break;
                //胜利
            case 1:
                g.drawImage(GameUtil.win,
                        GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                        GameUtil.OFFSET,GameUtil.SQUARE_LENGTH ,GameUtil.SQUARE_LENGTH, null);
                break;
                //失败
            case 2:
                g.drawImage(GameUtil.over,
                        GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                        GameUtil.OFFSET,GameUtil.SQUARE_LENGTH ,GameUtil.SQUARE_LENGTH, null);
                break;
            default:
        }
    }
}
