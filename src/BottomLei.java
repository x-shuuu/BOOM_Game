import java.io.StringReader;

//初始化地雷
public class BottomLei {
    //存放地雷坐标
    static int[] leis=new int [GameUtil.Lei_MAX*2];
    int x,y;
    //是否放置  t 可以放置  f 不可放置
    boolean isPlace=true;

    //重置雷
    void newLei()
    {
        for(int i=0;i<GameUtil.Lei_MAX*2;i+=2){
            x=(int)(Math.random()*GameUtil.MAP_W+1);//+1 0-11->1-12
            y=(int)(Math.random()*GameUtil.MAP_H+1);
            //格图大了一圈

            //判断随机的地雷坐标是否存在
            for(int j=0;j<i;j+=2)
            {
                if(x==leis[j]&&y==leis[j+1])
                {
                    i-=2;
                    isPlace=false;
                    break;
                }
            }
            //将坐标放入数组
            if(isPlace)
            {
                leis[i] = x;
                leis[i + 1] = y;
            }
            isPlace= true;
        }
        for(int i=0;i<GameUtil.Lei_MAX*2;i+=2) {
            GameUtil.DATA_BOTTON[leis[i]][leis[i + 1]]=-1;
        }
    }
}
