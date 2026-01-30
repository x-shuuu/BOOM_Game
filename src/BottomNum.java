//底层数字
public class BottomNum {
    void newNum()
    {
        for(int i=1;i<=GameUtil.MAP_W;i++)
        {
            for(int j=1;j<=GameUtil.MAP_H;j++)
            {
                if(GameUtil.DATA_BOTTON[i][j]==-1)
                {
                    for(int m=i-1;m<=i+1;m++)
                    {
                        for (int n=j-1;n<=j+1;n++)
                        {
                            if(GameUtil.DATA_BOTTON[m][n]>=0)
                            {
                                GameUtil.DATA_BOTTON[m][n]++;
                            }
                        }
                    }
                }
            }
        }
    }
}
