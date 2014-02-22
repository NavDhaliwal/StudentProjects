import javax.swing.ImageIcon ;
import java.io.*;
class Card implements Serializable
{
    ImageIcon image;
    int x;
    int y;
    int des;
	int xo;
	int yo;
	ImageIcon back=new ImageIcon("cards/"+"b.gif");
    public Card(ImageIcon image, int x, int y,int des)
    {
        this.image = image;
        this.x = x;
        this.y = y;
        this.des=des;
    }
    void setXY(int x, int y)
    {
		this.x = x ;
		this.y = y ;
	}
	void setorigin(int xo,int yo)
	{
		this.xo=xo;
		this.yo=yo;
	}
}