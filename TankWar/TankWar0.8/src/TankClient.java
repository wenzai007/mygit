import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


//让坦克能够顺着键盘的按键方向进行不同方向的移动  就是这样的！！！！

public class TankClient extends Frame{
	
	public static final int GAME_WIDTH=800;
	public static final int GAME_HEIGHT=600;
	

	
	Image offScreenImage=null;
	
	Tank mytank =new Tank(50,50);
	
	public void paint(Graphics g) {
		mytank.draw(g);
		
	}
	public void update(Graphics g) {
		if(offScreenImage==null){
			offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics goff=offScreenImage.getGraphics();
		Color c=goff.getColor();
		goff.setColor(Color.GREEN);
		goff.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		goff.setColor(c);//
		paint(goff);//?????这个地方为什么是goff呢？？？
		//答案见java本子总结21   因为前面都是画的一个图像即offscreenimg，所以
		//都是goff画笔！
		g.drawImage(offScreenImage, 0, 0, null);//在之前的frame上面
		//画一个  已经刚才画好的图像！！！
	}
	public void launchFrame(){
		this.setLocation(280,100);
		this.setSize(800, 600);
		this.setVisible(true);

		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);//????什么意思  整个程序都终止了？
			}
			
		});
		this.setBackground(Color.GREEN);
		this.setResizable(false);
		this.addKeyListener(new keymonitor());
		this.setVisible(true);
		new Thread(new paintThread()).start();
	}
	
	private class keymonitor extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			//System.out.println("yes"); //测试 是否添加成功
			mytank.pressed(e);
			}
		}
	
	private class paintThread implements Runnable {
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String args[]){
			TankClient tc=new TankClient();
			tc.launchFrame();
	}
}
