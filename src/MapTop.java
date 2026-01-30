import java.awt.*;

public class MapTop {

    int temp_x;
    int temp_Y;

    //重置游戏
    void reGame(){
        for(int i=1;i<=GameUtil.MAP_W;i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.DATA_TOP[i][j]=0;
            }
        }

    }

    //判断逻辑
    void logic(){
        temp_x=0;
        temp_Y=0;
        if(GameUtil.Mouse_X>GameUtil.OFFSET && GameUtil.Mouse_Y>GameUtil.OFFSET*3)
        {
            temp_x=(GameUtil.Mouse_X-GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH+1;
            temp_Y=(GameUtil.Mouse_Y-GameUtil.OFFSET*3)/GameUtil.SQUARE_LENGTH+1;
        }
        if(temp_x>=1 && temp_x<=GameUtil.MAP_W && temp_Y>=1 && temp_Y<=GameUtil.MAP_H){
            if(GameUtil.LEFT){
                //System.out.println(GameUtil.Mouse_X);
                //System.out.println(GameUtil.Mouse_Y);
                //覆盖则翻开
                if(GameUtil.DATA_TOP[temp_x][temp_Y]==0){
                    GameUtil.DATA_TOP[temp_x][temp_Y]=-1;
                }
                spaceOpen(temp_x,temp_Y);
                GameUtil.LEFT=false;
            }
            if(GameUtil.RIGHT){
                //System.out.println(GameUtil.Mouse_X);
                //System.out.println(GameUtil.Mouse_Y);
                //覆盖则插旗
                if(GameUtil.DATA_TOP[temp_x][temp_Y]==0){
                    GameUtil.DATA_TOP[temp_x][temp_Y]=1;
                    GameUtil.FLAG_NUM++;
                }
                //取消插旗
                else if(GameUtil.DATA_TOP[temp_x][temp_Y]==1){
                    GameUtil.DATA_TOP[temp_x][temp_Y]=0;
                    GameUtil.FLAG_NUM--;
                }
                else if(GameUtil.DATA_TOP[temp_x][temp_Y]==-1)
                {
                    numOpen(temp_x, temp_Y);
                }
                GameUtil.RIGHT=false;
            }
        }
        boom();
        victory();
    }

    //点击数字翻开
    void numOpen(int x,int y){
        int count=0;//记录旗数
        if(GameUtil.DATA_BOTTON[x][y]>0){
            for(int i=x-1;i<=x+1;i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if(GameUtil.DATA_TOP[i][j]==1){
                        count++;
                    }
                }
            }
            if(count==GameUtil.DATA_BOTTON[x][y]){
                for(int i=x-1;i<=x+1;i++){
                    for(int j=y-1;j<=y+1;j++) {
                        if (GameUtil.DATA_TOP[i][j] != 1) {
                            GameUtil.DATA_TOP[i][j] = -1;
                        }
                        //必须在雷区里
                        if (i >= 1 && j >= 1 && i <= GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    //游戏失败 t 失败 f 未失败
    boolean boom(){
        //所有雷都找到
        if(GameUtil.FLAG_NUM==GameUtil.Lei_MAX){
            for(int i=1;i<=GameUtil.MAP_W;i++){
                for(int j=1;j<=GameUtil.MAP_H;j++){
                    if(GameUtil.DATA_TOP[i][j]==0){
                        GameUtil.DATA_TOP[i][j]=-1;
                    }
                }
            }
        }
        for(int i=1;i<=GameUtil.MAP_W;i++){
            for(int j=1;j<=GameUtil.MAP_H;j++){
                if(GameUtil.DATA_BOTTON[i][j]==-1 && GameUtil.DATA_TOP[i][j]==-1){
                    //System.out.println("失败！");
                    GameUtil.state=2;
                    seeboom();
                    return true;
                }
            }
        }
        return false;
    }
    //失败显示游戏中所有的雷
    void seeboom(){
        for(int i=1;i<=GameUtil.MAP_W;i++){
            for(int j=1;j<=GameUtil.MAP_H;j++){
                //底层是雷，顶层不是旗 显示所有雷
                if(GameUtil.DATA_BOTTON[i][j]==-1 && GameUtil.DATA_TOP[i][j]!=1){
                    GameUtil.DATA_TOP[i][j]=-1;
                }
                //底层不是雷，顶层是旗 显示插错旗
                if(GameUtil.DATA_BOTTON[i][j]!=-1 && GameUtil.DATA_TOP[i][j]==1){
                    GameUtil.DATA_TOP[i][j]=2;
                }
            }
        }

    }
    //判断胜利  t 胜利  f  未胜利
    boolean victory(){
        //统计为打开格子数
        int count=0;
        for(int i=1;i<=GameUtil.MAP_W;i++){
            for(int j=1;j<=GameUtil.MAP_H;j++){
                if(GameUtil.DATA_TOP[i][j]!=-1){
                    count++;
                }
            }
        }
        if(count==GameUtil.Lei_MAX){
            //System.out.println("胜利！");
            GameUtil.state=1;
            for(int i=1;i<=GameUtil.MAP_W;i++){
                for(int j=1;j<=GameUtil.MAP_H;j++){
                    //未翻开的雷变成旗
                    if(GameUtil.DATA_TOP[i][j]==0){
                        GameUtil.DATA_TOP[i][j]=1;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //打开空格
    void spaceOpen(int x,int y){
        if(GameUtil.DATA_BOTTON[x][y]==0)
        {
            for(int i=x-1;i<=x+1;i++){
                for(int j=y-1;j<=y+1;j++){
                    //覆盖才递归
                    if(GameUtil.DATA_TOP[i][j]!=-1){
                        if(GameUtil.DATA_TOP[i][j]==1){
                            GameUtil.FLAG_NUM--;
                        }
                        GameUtil.DATA_TOP[i][j]=-1;
                        //必须在雷区里
                        if(i>=1&&j>=1&&i<=GameUtil.MAP_W&&j<=GameUtil.MAP_H){
                            spaceOpen(i,j);
                        }
                    }
                }
            }
        }
    }

    //绘制方法
    public void painSelf(Graphics g){
        logic();
        for(int i=1;i<=GameUtil.MAP_W;i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //覆盖
                if (GameUtil.DATA_TOP[i][j] == 0) {
                    //画图
                    g.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            null);
                }
                //插旗
                if (GameUtil.DATA_TOP[i][j] == 1) {
                    //画图
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            null);
                }
                //插错旗
                if (GameUtil.DATA_TOP[i][j] == 2) {
                    //画图
                    g.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            GameUtil.SQUARE_LENGTH - 1,
                            null);
                }
            }
        }
    }
}
