package dynamic_beat_final;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread  { //���α׷� �� �ϳ��� ���� ���α׷�
	
	private Player player;
	private boolean isLoop;	//���ѹݺ����� �ƴ���
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {	//������
		try {	//try-catch���� ����ó���� ���� ���
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name). toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);	//�ش� ���� ���ۿ� ��� �о�ü� �ְ� ��
			player = new Player (bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {	//�޼ҵ�, getTime : ����Ǵ� ������ � ��ġ�� ����ǰ� �ִ��� �˷���
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {	//close �Լ��� �������� ���� �����ϰ� ����
		isLoop = false;
		player.close();
		this.interrupt();	//�����ϰ� ����
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);	
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
