import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


//��̹���ܹ�˳�ż��̵İ���������в�ͬ������ƶ�  ���������ģ�������

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
		paint(goff);//?????����ط�Ϊʲô��goff�أ�����
		//�𰸼�java�����ܽ�21   ��Ϊǰ�涼�ǻ���һ��ͼ��offscreenimg������
		//����goff���ʣ�
		g.drawImage(offScreenImage, 0, 0, null);//��֮ǰ��frame����
		//��һ��  �Ѿ��ղŻ��õ�ͼ�񣡣���
	}
	public void launchFrame(){
		this.setLocation(280,100);
		this.setSize(800, 600);
		this.setVisible(true);

		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);//????ʲô��˼  ����������ֹ�ˣ�
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
			//System.out.println("yes"); //���� �Ƿ���ӳɹ�
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
