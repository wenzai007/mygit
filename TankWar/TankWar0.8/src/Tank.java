import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * @author Knight Rider
 * @version create time：2014年12月10日 下午9:48:03
 */
public class Tank {
	
	private final static int XSPEED=5,YSPEED=5;
	private boolean bL=false,bU=false,bR=false,bD=false;
	private enum Direction {L,U,R,D,LU,LD,RU,RD,STOP}
	private Direction dir = Direction.STOP;
	
	
	int x, y;//当前这个坦克的偏移量

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		// y+=5;// 这里就可以去掉了 用键盘控制方向
	}

	public void pressed(KeyEvent e) {//是一个监视器，不停的循环进行监视
		int pressNum = e.getKeyCode();
		switch (pressNum) {
		case KeyEvent.VK_LEFT:
			bL=true;
			break;
		case KeyEvent.VK_UP:
			bU=true;
			break;
		case KeyEvent.VK_RIGHT:
			bR=true;
			break;
		case KeyEvent.VK_DOWN:
			bD=true;
			break;
		}
		locateDirection();
		move();
	}
	
	public void locateDirection(){
		if(bL && !bU && !bR &&!bD){dir=Direction.L;return;}
		if(bL && bU && !bR &&!bD){dir=Direction.LU;return;}
		if(!bL && bU && !bR &&!bD){dir=Direction.U;return;}
		if(!bL && bU && bR &&!bD){dir=Direction.RU;return;}
		if(!bL && !bU && bR &&!bD){dir=Direction.R;return;}
		if(!bL && !bU && bR &&bD){dir=Direction.RD;return;}
		if(!bL && !bU && !bR &&bD){dir=Direction.D;return;}
		if(bL && !bU && !bR &&bD){dir=Direction.LD;return;}
	}
	
	public void move(){//移动之后全部都变成  false 是不是
		switch(dir){
		case L: x-=XSPEED;
				break;
		case R: x+=XSPEED;
				break;
		case U: y-=YSPEED;
				break;
		case D: y+=YSPEED;
				break;
		case LU:x-=XSPEED;
				y-=YSPEED;
				break;
		case RU:x+=XSPEED;
				y-=YSPEED;
				break;
		case LD:x-=XSPEED;
				y+=YSPEED;
				break;
		case RD:x+=XSPEED;
				y+=YSPEED;
				break;
		}
		dir=Direction.STOP;
		bL=bU=bR=bD=false;
	}

}
