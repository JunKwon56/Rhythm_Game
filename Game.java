package dynamic_beat_final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image scorebackgroundImage = new ImageIcon(Main.class.getResource("../images/scorebackground.png"))
			.getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	private int playtime;

	Score score = new Score();

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle, int playtime) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		this.playtime = playtime;
		gameMusic = new Music(this.musicTitle, false);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 50, 702);
		g.drawString(difficulty, 1000, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.white);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);

		// 점수
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(score.getScore(), 565, 702);

		g.drawImage(blueFlareImage, 220, 150, null);
		g.drawImage(judgeImage, 460, 420, null);

		// 화면에 보이기!!
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);
	}

	public void resultFrame(Graphics2D g) {

		String grade = null;
		int totalScore = Integer.parseInt(score.getScore());
		if (totalScore > (300 * 100 * 0.9)) {
			grade = "S";
		} else if (totalScore > (300 * 100 * 0.6)) {
			grade = "A";
		} else if (totalScore > (300 * 100 * 0.4)) {
			grade = "B";
		} else if (totalScore >= 0) {
			grade = "C";
		}
		g.drawImage(scorebackgroundImage, 253, 45, null);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.setColor(Color.white);
		g.drawString(score.getScore(), 500, 290);
		g.setColor(Color.pink);
		g.drawString(grade, 600, 400);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		Beat[] beats = null;

		if (titleName.equals("Siren_Hommies") && difficulty.equals("Easy")) {
			int startTime = 500;
			int gap = 125;
			beats = new Beat[] { new Beat(1290, "L"), new Beat(1820, "S"), new Beat(2470, "L"), new Beat(3110, "S"),
					new Beat(3830, "K"), new Beat(4490, "D"), new Beat(5330, "K"), new Beat(5940, "D"),
					new Beat(6740, "F"), new Beat(7410, "L"), new Beat(8080, "S"), new Beat(8770, "L"),
					new Beat(9390, "D"), new Beat(10070, "K"), new Beat(10740, "D"), new Beat(11430, "K"),
					new Beat(12240, "S"), new Beat(12540, "D"), new Beat(12880, "F"), new Beat(13210, "Space"),
					new Beat(13530, "J"), new Beat(13870, "K"), new Beat(14260, "L"), new Beat(14570, "K"),
					new Beat(14960, "J"), new Beat(15310, "Space"), new Beat(15630, "F"), new Beat(15950, "D"),
					new Beat(16340, "S"), new Beat(16660, "D"), new Beat(17050, "F"), new Beat(17400, "Space"),
					new Beat(17650, "D"), new Beat(17980, "Space"), new Beat(18300, "J"), new Beat(18310, "L"),
					new Beat(18680, "D"), new Beat(19050, "Space"), new Beat(19430, "D"), new Beat(19730, "J"),
					new Beat(19750, "L"), new Beat(20060, "D"), new Beat(20460, "Space"), new Beat(20770, "D"),
					new Beat(21130, "L"), new Beat(21150, "J"), new Beat(21510, "S"), new Beat(21810, "F"),
					new Beat(22170, "S"), new Beat(22520, "L"), new Beat(22530, "J"), new Beat(22850, "S"),
					new Beat(23200, "F"), new Beat(23540, "D"), new Beat(23880, "J"), new Beat(23900, "K"),
					new Beat(24250, "D"), new Beat(24600, "Space"), new Beat(24910, "F"), new Beat(25230, "L"),
					new Beat(25250, "J"), new Beat(25620, "F"), new Beat(25940, "D"), new Beat(26270, "F"),
					new Beat(26620, "J"), new Beat(26620, "K"), new Beat(26970, "D"), new Beat(27350, "Space"),
					new Beat(27650, "S"), new Beat(28010, "F"), new Beat(28030, "S"), new Beat(28360, "D"),
					new Beat(28710, "F"), new Beat(29050, "D"), new Beat(29380, "L"), new Beat(29380, "J"),
					new Beat(29770, "S"), new Beat(30140, "F"), new Beat(30480, "D"), new Beat(30830, "J"),
					new Beat(30850, "K"), new Beat(31180, "D"), new Beat(31530, "Space"), new Beat(31900, "D"),
					new Beat(32250, "S"), new Beat(32260, "F"), new Beat(32610, "D"), new Beat(33160, "Space"),
					new Beat(33380, "F"), new Beat(33710, "K"), new Beat(33710, "J"), new Beat(34020, "D"),
					new Beat(34330, "F"), new Beat(34610, "S"), new Beat(34980, "K"), new Beat(35000, "L"),
					new Beat(35360, "D"), new Beat(35710, "F"), new Beat(36010, "S"), new Beat(36380, "Space"),
					new Beat(36450, "S"), new Beat(36730, "D"), new Beat(37120, "F"), new Beat(37460, "D"),
					new Beat(37780, "J"), new Beat(37800, "K"), new Beat(38150, "D"), new Beat(38560, "F"),
					new Beat(38880, "S"), new Beat(39250, "L"), new Beat(39550, "K"), new Beat(39910, "J"),
					new Beat(40260, "Space"), new Beat(40610, "D"), new Beat(40940, "F"), new Beat(41350, "Space"),
					new Beat(41650, "J"), new Beat(42040, "Space"), new Beat(42350, "J"), new Beat(42720, "K"),
					new Beat(43050, "L"), new Beat(43410, "K"), new Beat(43750, "L"), new Beat(44080, "K"),
					new Beat(44410, "J"), new Beat(44750, "S"), new Beat(45080, "D"), new Beat(45410, "Space"),
					new Beat(45730, "D"), new Beat(46100, "F"), new Beat(46500, "S"), new Beat(46830, "D"),
					new Beat(47180, "K"), new Beat(47200, "J"), new Beat(47580, "F"), new Beat(47900, "D"),
					new Beat(48230, "Space"), new Beat(48570, "D"), new Beat(48910, "S"), new Beat(49240, "F"),
					new Beat(49680, "S"), new Beat(49680, "D"), new Beat(50010, "D"), new Beat(50380, "F"),
					new Beat(50710, "D"), new Beat(51030, "K"), new Beat(51380, "J"), new Beat(51750, "J"),
					new Beat(51760, "L"), new Beat(52080, "K"), new Beat(52450, "J"), new Beat(52750, "D"),
					new Beat(53130, "F"), new Beat(53150, "S"), new Beat(53450, "D"), new Beat(53780, "Space"),
					new Beat(54100, "D"), new Beat(54460, "J"), new Beat(54500, "L"), new Beat(54880, "K"),
					new Beat(55230, "J"), new Beat(55530, "D"), new Beat(55900, "Space"), new Beat(56250, "D"),
					new Beat(56650, "F"), new Beat(56930, "D"), new Beat(57280, "S"), new Beat(57660, "F"),
					new Beat(58010, "J"), new Beat(58010, "L"), new Beat(58380, "K"), new Beat(58680, "D"),
					new Beat(59030, "Space"), new Beat(59380, "S"), new Beat(59410, "L"), new Beat(59930, "L"),
					new Beat(59970, "L"), new Beat(59980, "L"), new Beat(60200, "D"), new Beat(60200, "K"),
					new Beat(60880, "F"), new Beat(60900, "J"), new Beat(61560, "Space"), new Beat(62290, "F"),
					new Beat(62580, "D"), new Beat(62920, "L"), new Beat(62940, "J"), new Beat(63250, "S"),
					new Beat(63610, "K"), new Beat(63950, "D"), new Beat(64270, "L"), new Beat(64290, "J"),
					new Beat(64670, "F"), new Beat(65030, "D"), new Beat(65350, "K"), new Beat(65700, "L"),
					new Beat(65720, "J"), new Beat(66050, "K"), new Beat(66350, "J"), new Beat(66700, "L"),
					new Beat(67030, "Space"), new Beat(67450, "F"), new Beat(67450, "S"), new Beat(67800, "D"),
					new Beat(68110, "K"), new Beat(68380, "J"), new Beat(68800, "S"), new Beat(68800, "F"),
					new Beat(69150, "D"), new Beat(69530, "Space"), new Beat(69860, "L"), new Beat(69860, "J"),
					new Beat(70210, "D"), new Beat(70590, "Space"), new Beat(70870, "K"), new Beat(71250, "S"),
					new Beat(71270, "F"), new Beat(71580, "D"), new Beat(71970, "Space"), new Beat(72290, "J"),
					new Beat(72670, "K"), new Beat(73020, "J"), new Beat(73020, "L"), new Beat(73350, "D"),
					new Beat(73700, "Space"), new Beat(74070, "K"), new Beat(74380, "F"), new Beat(74400, "S"),
					new Beat(74770, "D"), new Beat(75120, "Space"), new Beat(75450, "L"), new Beat(75450, "J"),
					new Beat(75800, "K"), new Beat(76140, "J"), new Beat(76470, "D"), new Beat(76840, "Space"),
					new Beat(76850, "D"), new Beat(77250, "J"), new Beat(77550, "S"), new Beat(77900, "J"),
					new Beat(77900, "L"), new Beat(78270, "D"), new Beat(78580, "K"), new Beat(78950, "F"),
					new Beat(79290, "J"), new Beat(79620, "L"), new Beat(79640, "J"), new Beat(80000, "S"),
					new Beat(80370, "D"), new Beat(80680, "Space"), new Beat(81030, "J"), new Beat(81050, "K"),
					new Beat(81380, "Space"), new Beat(81720, "F"), new Beat(82030, "D"), new Beat(82450, "S"),
					new Beat(82460, "D"), new Beat(82480, "Space"), new Beat(82780, "D"), new Beat(83150, "F"),
					new Beat(83470, "L"), new Beat(83470, "J"), new Beat(83820, "K"), new Beat(84170, "J"),
					new Beat(84500, "L"), new Beat(84870, "K"), new Beat(85220, "S"), new Beat(85570, "D"),
					new Beat(85910, "F"), new Beat(86250, "Space"), new Beat(86580, "L"), new Beat(86930, "K"),
					new Beat(87300, "J"), new Beat(87620, "L"), new Beat(88000, "K"), new Beat(88380, "J"),
					new Beat(88690, "Space"), new Beat(89010, "F"), new Beat(89300, "D"), new Beat(89670, "S"),
					new Beat(90030, "S"), new Beat(90400, "D"), new Beat(90750, "F"), new Beat(91090, "Space"),
					new Beat(91440, "J"), new Beat(91770, "K"), new Beat(92120, "L"), new Beat(92470, "K"),
					new Beat(92880, "J"), new Beat(93230, "Space"), new Beat(93600, "L"), new Beat(93600, "J"),
					new Beat(93950, "S"), new Beat(93970, "F"), new Beat(94300, "D"), new Beat(94650, "K"),
					new Beat(95050, "Space"), new Beat(95050, "D"), new Beat(95370, "K"), new Beat(95710, "F"),
					new Beat(96070, "L"), new Beat(96090, "J"), new Beat(96450, "D"), new Beat(96750, "Space"),
					new Beat(97090, "F"), new Beat(97110, "S"), new Beat(97420, "D"), new Beat(97780, "Space"),
					new Beat(98090, "K"), new Beat(98450, "K"), new Beat(98470, "J"), new Beat(98810, "D"),
					new Beat(99130, "Space"), new Beat(99450, "F"), new Beat(99820, "S"), new Beat(99820, "F"),
					new Beat(100170, "Space"), new Beat(100470, "D"), new Beat(100870, "L"), new Beat(100870, "J"),
					new Beat(101200, "K"), new Beat(101550, "J"), new Beat(101920, "Space"), new Beat(101940, "F"),
					new Beat(102270, "D"), new Beat(102580, "F"), new Beat(102880, "S"), new Beat(103270, "J"),
					new Beat(103270, "K"), new Beat(103620, "D"), new Beat(103950, "Space"), new Beat(104290, "D"),
					new Beat(104630, "L"), new Beat(104650, "J"), new Beat(105040, "F"), new Beat(105050, "S"),
					new Beat(105390, "D"), new Beat(105720, "K"), new Beat(106090, "Space"), new Beat(106450, "K"),
					new Beat(106850, "D"), new Beat(107170, "J"), new Beat(107170, "K"), new Beat(107550, "F"),
					new Beat(107570, "S"), new Beat(107880, "D"), new Beat(108220, "L"), new Beat(108540, "K"),
					new Beat(108870, "K"), new Beat(108870, "J"), new Beat(109200, "D"), new Beat(109550, "Space"),
					new Beat(109880, "L"), new Beat(110250, "L"), new Beat(110270, "J"), new Beat(110650, "D"),
					new Beat(110990, "Space"), new Beat(111350, "S"), new Beat(111680, "J"), new Beat(111700, "K"),
					new Beat(112030, "D"), new Beat(112400, "Space"), new Beat(112710, "F"), new Beat(113120, "L"),
					new Beat(113140, "J"), new Beat(113170, "F"), new Beat(113170, "S"), new Beat(113470, "K"),
					new Beat(113800, "J"), new Beat(114140, "D"), new Beat(114470, "L"), new Beat(114810, "K"),
					new Beat(115150, "J"), new Beat(115470, "S"), new Beat(115850, "J"), new Beat(116240, "K"),
					new Beat(116600, "L"), new Beat(116980, "K"), new Beat(117320, "D"), new Beat(117640, "K"),
					new Beat(117970, "Space"), new Beat(118310, "J"), new Beat(118650, "K"), new Beat(118990, "L"),
					new Beat(119350, "S"), new Beat(119740, "K"), new Beat(120070, "L"), new Beat(120090, "J"),
					new Beat(120440, "D"), new Beat(120800, "Space"), new Beat(121110, "K"), new Beat(121450, "S"),
					new Beat(121800, "F"), new Beat(122150, "D"), new Beat(122470, "L"), new Beat(122840, "K"),
					new Beat(123190, "J"), new Beat(123570, "F"), new Beat(123920, "J"), new Beat(124270, "D"),
					new Beat(124580, "K"), new Beat(124970, "L"), new Beat(124980, "J"), new Beat(125320, "K"),
					new Beat(125670, "J"), new Beat(126020, "D"), new Beat(126340, "L"), new Beat(126350, "J"),
					new Beat(126700, "K"), new Beat(127020, "D"), new Beat(127380, "J"), new Beat(127690, "K"),
					new Beat(128050, "L"), new Beat(128440, "K"), new Beat(128790, "J"), new Beat(129150, "Space"),
					new Beat(129470, "D"), new Beat(129820, "S"), new Beat(130160, "D"), new Beat(130490, "K"),
					new Beat(130800, "D"), new Beat(131170, "L"), new Beat(131520, "K"), new Beat(131890, "J"),
					new Beat(132220, "Space"), new Beat(132590, "J"), new Beat(132940, "K"), new Beat(133290, "L"),
					new Beat(133590, "K"), new Beat(133950, "D"), new Beat(134320, "J"), new Beat(134330, "K"),
					new Beat(134710, "F"), new Beat(135060, "L"), new Beat(135390, "L"), new Beat(135410, "J"),
					new Beat(135790, "D"), new Beat(136120, "K"), new Beat(136420, "J"), new Beat(136790, "S"),
					new Beat(136790, "F"), new Beat(137140, "K"), new Beat(137490, "J"), new Beat(137790, "D"),
					new Beat(138140, "L"), new Beat(138160, "J"), new Beat(138520, "D"), new Beat(138870, "K"),
					new Beat(139270, "S"), new Beat(139270, "F"), new Beat(139610, "D"), new Beat(139960, "K"),
					new Beat(140700, "K"), new Beat(141020, "D"), new Beat(141380, "J"), new Beat(141690, "K"),
					new Beat(143050, "L"), new Beat(143440, "K"), new Beat(143790, "J"), new Beat(143150, "Space"),
					new Beat(144700, "K"), new Beat(145020, "D"), new Beat(145380, "J"), new Beat(145690, "K"),
					new Beat(148050, "L"), new Beat(148440, "K"), new Beat(148790, "J"), new Beat(149150, "Space"),
					new Beat(149470, "D"), new Beat(149820, "S"), new Beat(150160, "D"), new Beat(150490, "K"),
					new Beat(150800, "D"), new Beat(151170, "L"), new Beat(151520, "K"), new Beat(151890, "J"),
					new Beat(152220, "Space"), new Beat(152590, "J"), new Beat(152940, "K"), new Beat(153290, "L"),
					new Beat(153590, "K"), new Beat(153950, "D"), new Beat(154320, "J"), new Beat(154330, "K"), };
		} else if (titleName.equals("Siren_Hommies") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(1290, "L"), new Beat(1820, "S"), new Beat(2470, "L"), new Beat(3110, "S"),
					new Beat(3830, "K"), new Beat(4490, "D"), new Beat(5330, "K"), new Beat(5940, "D"),
					new Beat(6740, "F"), new Beat(7410, "L"), new Beat(8080, "S"), new Beat(8770, "L"),
					new Beat(9390, "D"), new Beat(10070, "K"), new Beat(10740, "D"), new Beat(11430, "K"),
					new Beat(12240, "S"), new Beat(12540, "D"), new Beat(12880, "F"), new Beat(13210, "Space"),
					new Beat(13530, "J"), new Beat(13870, "K"), new Beat(14260, "L"), new Beat(14570, "K"),
					new Beat(14960, "J"), new Beat(15310, "Space"), new Beat(15630, "F"), new Beat(15950, "D"),
					new Beat(16340, "S"), new Beat(16660, "D"), new Beat(17050, "F"), new Beat(17400, "Space"),
					new Beat(17650, "D"), new Beat(17980, "Space"), new Beat(18300, "J"), new Beat(18310, "L"),
					new Beat(18680, "D"), new Beat(19050, "Space"), new Beat(19430, "D"), new Beat(19730, "J"),
					new Beat(19750, "L"), new Beat(20060, "D"), new Beat(20460, "Space"), new Beat(20770, "D"),
					new Beat(21130, "L"), new Beat(21150, "J"), new Beat(21510, "S"), new Beat(21810, "F"),
					new Beat(22170, "S"), new Beat(22520, "L"), new Beat(22530, "J"), new Beat(22850, "S"),
					new Beat(23200, "F"), new Beat(23540, "D"), new Beat(23880, "J"), new Beat(23900, "K"),
					new Beat(24250, "D"), new Beat(24600, "Space"), new Beat(24910, "F"), new Beat(25230, "L"),
					new Beat(25250, "J"), new Beat(25620, "F"), new Beat(25940, "D"), new Beat(26270, "F"),
					new Beat(26620, "J"), new Beat(26620, "K"), new Beat(26970, "D"), new Beat(27350, "Space"),
					new Beat(27650, "S"), new Beat(28010, "F"), new Beat(28030, "S"), new Beat(28360, "D"),
					new Beat(28710, "F"), new Beat(29050, "D"), new Beat(29380, "L"), new Beat(29380, "J"),
					new Beat(29770, "S"), new Beat(30140, "F"), new Beat(30480, "D"), new Beat(30830, "J"),
					new Beat(30850, "K"), new Beat(31180, "D"), new Beat(31530, "Space"), new Beat(31900, "D"),
					new Beat(32250, "S"), new Beat(32260, "F"), new Beat(32610, "D"), new Beat(33160, "Space"),
					new Beat(33380, "F"), new Beat(33710, "K"), new Beat(33710, "J"), new Beat(34020, "D"),
					new Beat(34330, "F"), new Beat(34610, "S"), new Beat(34980, "K"), new Beat(35000, "L"),
					new Beat(35360, "D"), new Beat(35710, "F"), new Beat(36010, "S"), new Beat(36380, "Space"),
					new Beat(36450, "S"), new Beat(36730, "D"), new Beat(37120, "F"), new Beat(37460, "D"),
					new Beat(37780, "J"), new Beat(37800, "K"), new Beat(38150, "D"), new Beat(38560, "F"),
					new Beat(38880, "S"), new Beat(39250, "L"), new Beat(39550, "K"), new Beat(39910, "J"),
					new Beat(40260, "Space"), new Beat(40610, "D"), new Beat(40940, "F"), new Beat(41350, "Space"),
					new Beat(41650, "J"), new Beat(42040, "Space"), new Beat(42350, "J"), new Beat(42720, "K"),
					new Beat(43050, "L"), new Beat(43410, "K"), new Beat(43750, "L"), new Beat(44080, "K"),
					new Beat(44410, "J"), new Beat(44750, "S"), new Beat(45080, "D"), new Beat(45410, "Space"),
					new Beat(45730, "D"), new Beat(46100, "F"), new Beat(46500, "S"), new Beat(46830, "D"),
					new Beat(47180, "K"), new Beat(47200, "J"), new Beat(47580, "F"), new Beat(47900, "D"),
					new Beat(48230, "Space"), new Beat(48570, "D"), new Beat(48910, "S"), new Beat(49240, "F"),
					new Beat(49680, "S"), new Beat(49680, "D"), new Beat(50010, "D"), new Beat(50380, "F"),
					new Beat(50710, "D"), new Beat(51030, "K"), new Beat(51380, "J"), new Beat(51750, "J"),
					new Beat(51760, "L"), new Beat(52080, "K"), new Beat(52450, "J"), new Beat(52750, "D"),
					new Beat(53130, "F"), new Beat(53150, "S"), new Beat(53450, "D"), new Beat(53780, "Space"),
					new Beat(54100, "D"), new Beat(54460, "J"), new Beat(54500, "L"), new Beat(54880, "K"),
					new Beat(55230, "J"), new Beat(55530, "D"), new Beat(55900, "Space"), new Beat(56250, "D"),
					new Beat(56650, "F"), new Beat(56930, "D"), new Beat(57280, "S"), new Beat(57660, "F"),
					new Beat(58010, "J"), new Beat(58010, "L"), new Beat(58380, "K"), new Beat(58680, "D"),
					new Beat(59030, "Space"), new Beat(59380, "S"), new Beat(59410, "L"), new Beat(59930, "L"),
					new Beat(59970, "L"), new Beat(59980, "L"), new Beat(60200, "D"), new Beat(60200, "K"),
					new Beat(60880, "F"), new Beat(60900, "J"), new Beat(61560, "Space"), new Beat(62290, "F"),
					new Beat(62580, "D"), new Beat(62920, "L"), new Beat(62940, "J"), new Beat(63250, "S"),
					new Beat(63610, "K"), new Beat(63950, "D"), new Beat(64270, "L"), new Beat(64290, "J"),
					new Beat(64670, "F"), new Beat(65030, "D"), new Beat(65350, "K"), new Beat(65700, "L"),
					new Beat(65720, "J"), new Beat(66050, "K"), new Beat(66350, "J"), new Beat(66700, "L"),
					new Beat(67030, "Space"), new Beat(67450, "F"), new Beat(67450, "S"), new Beat(67800, "D"),
					new Beat(68110, "K"), new Beat(68380, "J"), new Beat(68800, "S"), new Beat(68800, "F"),
					new Beat(69150, "D"), new Beat(69530, "Space"), new Beat(69860, "L"), new Beat(69860, "J"),
					new Beat(70210, "D"), new Beat(70590, "Space"), new Beat(70870, "K"), new Beat(71250, "S"),
					new Beat(71270, "F"), new Beat(71580, "D"), new Beat(71970, "Space"), new Beat(72290, "J"),
					new Beat(72670, "K"), new Beat(73020, "J"), new Beat(73020, "L"), new Beat(73350, "D"),
					new Beat(73700, "Space"), new Beat(74070, "K"), new Beat(74380, "F"), new Beat(74400, "S"),
					new Beat(74770, "D"), new Beat(75120, "Space"), new Beat(75450, "L"), new Beat(75450, "J"),
					new Beat(75800, "K"), new Beat(76140, "J"), new Beat(76470, "D"), new Beat(76840, "Space"),
					new Beat(76850, "D"), new Beat(77250, "J"), new Beat(77550, "S"), new Beat(77900, "J"),
					new Beat(77900, "L"), new Beat(78270, "D"), new Beat(78580, "K"), new Beat(78950, "F"),
					new Beat(79290, "J"), new Beat(79620, "L"), new Beat(79640, "J"), new Beat(80000, "S"),
					new Beat(80370, "D"), new Beat(80680, "Space"), new Beat(81030, "J"), new Beat(81050, "K"),
					new Beat(81380, "Space"), new Beat(81720, "F"), new Beat(82030, "D"), new Beat(82450, "S"),
					new Beat(82460, "D"), new Beat(82480, "Space"), new Beat(82780, "D"), new Beat(83150, "F"),
					new Beat(83470, "L"), new Beat(83470, "J"), new Beat(83820, "K"), new Beat(84170, "J"),
					new Beat(84500, "L"), new Beat(84870, "K"), new Beat(85220, "S"), new Beat(85570, "D"),
					new Beat(85910, "F"), new Beat(86250, "Space"), new Beat(86580, "L"), new Beat(86930, "K"),
					new Beat(87300, "J"), new Beat(87620, "L"), new Beat(88000, "K"), new Beat(88380, "J"),
					new Beat(88690, "Space"), new Beat(89010, "F"), new Beat(89300, "D"), new Beat(89670, "S"),
					new Beat(90030, "S"), new Beat(90400, "D"), new Beat(90750, "F"), new Beat(91090, "Space"),
					new Beat(91440, "J"), new Beat(91770, "K"), new Beat(92120, "L"), new Beat(92470, "K"),
					new Beat(92880, "J"), new Beat(93230, "Space"), new Beat(93600, "L"), new Beat(93600, "J"),
					new Beat(93950, "S"), new Beat(93970, "F"), new Beat(94300, "D"), new Beat(94650, "K"),
					new Beat(95050, "Space"), new Beat(95050, "D"), new Beat(95370, "K"), new Beat(95710, "F"),
					new Beat(96070, "L"), new Beat(96090, "J"), new Beat(96450, "D"), new Beat(96750, "Space"),
					new Beat(97090, "F"), new Beat(97110, "S"), new Beat(97420, "D"), new Beat(97780, "Space"),
					new Beat(98090, "K"), new Beat(98450, "K"), new Beat(98470, "J"), new Beat(98810, "D"),
					new Beat(99130, "Space"), new Beat(99450, "F"), new Beat(99820, "S"), new Beat(99820, "F"),
					new Beat(100170, "Space"), new Beat(100470, "D"), new Beat(100870, "L"), new Beat(100870, "J"),
					new Beat(101200, "K"), new Beat(101550, "J"), new Beat(101920, "Space"), new Beat(101940, "F"),
					new Beat(102270, "D"), new Beat(102580, "F"), new Beat(102880, "S"), new Beat(103270, "J"),
					new Beat(103270, "K"), new Beat(103620, "D"), new Beat(103950, "Space"), new Beat(104290, "D"),
					new Beat(104630, "L"), new Beat(104650, "J"), new Beat(105040, "F"), new Beat(105050, "S"),
					new Beat(105390, "D"), new Beat(105720, "K"), new Beat(106090, "Space"), new Beat(106450, "K"),
					new Beat(106850, "D"), new Beat(107170, "J"), new Beat(107170, "K"), new Beat(107550, "F"),
					new Beat(107570, "S"), new Beat(107880, "D"), new Beat(108220, "L"), new Beat(108540, "K"),
					new Beat(108870, "K"), new Beat(108870, "J"), new Beat(109200, "D"), new Beat(109550, "Space"),
					new Beat(109880, "L"), new Beat(110250, "L"), new Beat(110270, "J"), new Beat(110650, "D"),
					new Beat(110990, "Space"), new Beat(111350, "S"), new Beat(111680, "J"), new Beat(111700, "K"),
					new Beat(112030, "D"), new Beat(112400, "Space"), new Beat(112710, "F"), new Beat(113120, "L"),
					new Beat(113140, "J"), new Beat(113170, "F"), new Beat(113170, "S"), new Beat(113470, "K"),
					new Beat(113800, "J"), new Beat(114140, "D"), new Beat(114470, "L"), new Beat(114810, "K"),
					new Beat(115150, "J"), new Beat(115470, "S"), new Beat(115850, "J"), new Beat(116240, "K"),
					new Beat(116600, "L"), new Beat(116980, "K"), new Beat(117320, "D"), new Beat(117640, "K"),
					new Beat(117970, "Space"), new Beat(118310, "J"), new Beat(118650, "K"), new Beat(118990, "L"),
					new Beat(119350, "S"), new Beat(119740, "K"), new Beat(120070, "L"), new Beat(120090, "J"),
					new Beat(120440, "D"), new Beat(120800, "Space"), new Beat(121110, "K"), new Beat(121450, "S"),
					new Beat(121800, "F"), new Beat(122150, "D"), new Beat(122470, "L"), new Beat(122840, "K"),
					new Beat(123190, "J"), new Beat(123570, "F"), new Beat(123920, "J"), new Beat(124270, "D"),
					new Beat(124580, "K"), new Beat(124970, "L"), new Beat(124980, "J"), new Beat(125320, "K"),
					new Beat(125670, "J"), new Beat(126020, "D"), new Beat(126340, "L"), new Beat(126350, "J"),
					new Beat(126700, "K"), new Beat(127020, "D"), new Beat(127380, "J"), new Beat(127690, "K"),
					new Beat(128050, "L"), new Beat(128440, "K"), new Beat(128790, "J"), new Beat(129150, "Space"),
					new Beat(129470, "D"), new Beat(129820, "S"), new Beat(130160, "D"), new Beat(130490, "K"),
					new Beat(130800, "D"), new Beat(131170, "L"), new Beat(131520, "K"), new Beat(131890, "J"),
					new Beat(132220, "Space"), new Beat(132590, "J"), new Beat(132940, "K"), new Beat(133290, "L"),
					new Beat(133590, "K"), new Beat(133950, "D"), new Beat(134320, "J"), new Beat(134330, "K"),
					new Beat(134710, "F"), new Beat(135060, "L"), new Beat(135390, "L"), new Beat(135410, "J"),
					new Beat(135790, "D"), new Beat(136120, "K"), new Beat(136420, "J"), new Beat(136790, "S"),
					new Beat(136790, "F"), new Beat(137140, "K"), new Beat(137490, "J"), new Beat(137790, "D"),
					new Beat(138140, "L"), new Beat(138160, "J"), new Beat(138520, "D"), new Beat(138870, "K"),
					new Beat(139270, "S"), new Beat(139270, "F"), new Beat(139610, "D"), new Beat(139960, "K"),
					new Beat(140700, "K"), new Beat(141020, "D"), new Beat(141380, "J"), new Beat(141690, "K"),
					new Beat(143050, "L"), new Beat(143440, "K"), new Beat(143790, "J"), new Beat(143150, "Space"),
					new Beat(144700, "K"), new Beat(145020, "D"), new Beat(145380, "J"), new Beat(145690, "K"),
					new Beat(148050, "L"), new Beat(148440, "K"), new Beat(148790, "J"), new Beat(149150, "Space"),
					new Beat(149470, "D"), new Beat(149820, "S"), new Beat(150160, "D"), new Beat(150490, "K"),
					new Beat(150800, "D"), new Beat(151170, "L"), new Beat(151520, "K"), new Beat(151890, "J"),
					new Beat(152220, "Space"), new Beat(152590, "J"), new Beat(152940, "K"), new Beat(153290, "L"),
					new Beat(153590, "K"), new Beat(153950, "D"), new Beat(154320, "J"), new Beat(154330, "K"), };
		} else if (titleName.equals("Rollin_BraveGirls") && difficulty.equals("Easy")) {
			int startTime = 1000;
			int gap = 125;
			beats = new Beat[] { new Beat(6620, "S"), new Beat(7260, "L"), new Beat(7960, "S"), new Beat(8730, "L"),
					new Beat(9410, "Space"), new Beat(10100, "K"), new Beat(10780, "D"), new Beat(11520, "J"),
					new Beat(12230, "Space"), new Beat(12900, "L"), new Beat(13540, "S"), new Beat(14330, "J"),
					new Beat(15050, "Space"), new Beat(15720, "K"), new Beat(16380, "Space"), new Beat(17120, "F"),
					new Beat(18320, "Space"), new Beat(19040, "S"), new Beat(19680, "L"), new Beat(20400, "Space"),
					new Beat(21130, "S"), new Beat(21130, "D"), new Beat(21830, "Space"), new Beat(22520, "K"),
					new Beat(22520, "L"), new Beat(23210, "S"), new Beat(23900, "K"), new Beat(23900, "L"),
					new Beat(24580, "D"), new Beat(25290, "K"), new Beat(25300, "L"), new Beat(26040, "J"),
					new Beat(26710, "D"), new Beat(26730, "F"), new Beat(27440, "J"), new Beat(28100, "D"),
					new Beat(28120, "S"), new Beat(28840, "J"), new Beat(29550, "F"), new Beat(29560, "J"),
					new Beat(29560, "D"), new Beat(29580, "K"), new Beat(30240, "S"), new Beat(30930, "L"),
					new Beat(31630, "Space"), new Beat(32290, "K"), new Beat(33000, "D"), new Beat(33730, "K"),
					new Beat(33750, "L"), new Beat(34420, "D"), new Beat(35120, "K"), new Beat(35120, "J"),
					new Beat(35820, "Space"), new Beat(36500, "S"), new Beat(37170, "Space"), new Beat(37920, "J"),
					new Beat(37920, "K"), new Beat(38590, "D"), new Beat(39250, "Space"), new Beat(39250, "D"),
					new Beat(39970, "J"), new Beat(40690, "J"), new Beat(40700, "L"), new Beat(41350, "D"),
					new Beat(42050, "J"), new Beat(42080, "F"), new Beat(42080, "S"), new Beat(42100, "K"),
					new Beat(42800, "D"), new Beat(43510, "J"), new Beat(43510, "L"), new Beat(44170, "D"),
					new Beat(44860, "K"), new Beat(45520, "S"), new Beat(46260, "J"), new Beat(46960, "L"),
					new Beat(47640, "D"), new Beat(48320, "K"), new Beat(49050, "Space"), new Beat(49770, "J"),
					new Beat(50490, "S"), new Beat(50510, "D"), new Beat(51150, "Space"), new Beat(51820, "J"),
					new Beat(52540, "D"), new Beat(53190, "K"), new Beat(53200, "L"), new Beat(53870, "Space"),
					new Beat(54590, "K"), new Beat(55310, "D"), new Beat(55990, "J"), new Beat(56690, "S"),
					new Beat(56710, "D"), new Beat(57390, "Space"), new Beat(58070, "J"), new Beat(58780, "F"),
					new Beat(58800, "D"), new Beat(59440, "K"), new Beat(60170, "Space"), new Beat(60910, "J"),
					new Beat(61590, "F"), new Beat(61610, "D"), new Beat(62250, "K"), new Beat(62970, "Space"),
					new Beat(63690, "J"), new Beat(64340, "D"), new Beat(65060, "S"), new Beat(65760, "L"),
					new Beat(65760, "J"), new Beat(66420, "D"), new Beat(67130, "K"), new Beat(67840, "F"),
					new Beat(68570, "J"), new Beat(68580, "K"), new Beat(69240, "D"), new Beat(69920, "L"),
					new Beat(70620, "S"), new Beat(71390, "J"), new Beat(72040, "D"), new Beat(72700, "K"),
					new Beat(73370, "Space"), new Beat(74070, "J"), new Beat(74810, "S"), new Beat(75510, "L"),
					new Beat(76170, "D"), new Beat(76930, "K"), new Beat(76950, "J"), new Beat(77570, "D"),
					new Beat(78270, "K"), new Beat(78300, "L"), new Beat(78990, "D"), new Beat(79690, "F"),
					new Beat(80340, "D"), new Beat(81100, "Space"), new Beat(81130, "K"), new Beat(81130, "J"),
					new Beat(81790, "D"), new Beat(82440, "K"), new Beat(83170, "L"), new Beat(83870, "S"),
					new Beat(84540, "K"), new Beat(85220, "Space"), new Beat(85980, "J"), new Beat(86610, "D"),
					new Beat(87260, "L"), new Beat(88040, "K"), new Beat(88760, "J"), new Beat(89410, "S"),
					new Beat(90090, "D"), new Beat(90830, "F"), new Beat(91490, "L"), new Beat(92170, "Space"),
					new Beat(92870, "K"), new Beat(93570, "S"), new Beat(94290, "D"), new Beat(94970, "Space"),
					new Beat(95660, "S"), new Beat(96380, "Space"), new Beat(97060, "K"), new Beat(97760, "S"),
					new Beat(98510, "Space"), new Beat(99170, "L"), new Beat(99890, "Space"), new Beat(100550, "K"),
					new Beat(101240, "Space"), new Beat(101980, "J"), new Beat(102670, "Space"), new Beat(103360, "D"),
					new Beat(104020, "Space"), new Beat(104740, "L"), new Beat(105440, "Space"), new Beat(106110, "K"),
					new Beat(106910, "Space"), new Beat(107590, "S"), new Beat(108290, "Space"), new Beat(108970, "L"),
					new Beat(109630, "K"), new Beat(110370, "D"), new Beat(111040, "Space"), new Beat(111730, "S"),
					new Beat(112440, "Space"), new Beat(113160, "L"), new Beat(113840, "K"), new Beat(114560, "J"),
					new Beat(115210, "Space"), new Beat(115930, "D"), new Beat(116640, "Space"), new Beat(117300, "K"),
					new Beat(117990, "Space"), new Beat(118710, "L"), new Beat(119410, "Space"), new Beat(120100, "K"),
					new Beat(120800, "Space"), new Beat(121580, "S"), new Beat(122180, "Space"), new Beat(122830, "J"),
					new Beat(123630, "Space"), new Beat(124230, "K"), new Beat(125000, "Space"), new Beat(125760, "D"),
					new Beat(126380, "Space"), new Beat(127060, "J"), new Beat(127810, "Space"), new Beat(128480, "K"),
					new Beat(129180, "Space"), new Beat(129860, "L"), new Beat(130560, "Space"), new Beat(131260, "K"),
					new Beat(131280, "J"), new Beat(131940, "Space"), new Beat(132650, "S"), new Beat(132660, "D"),
					new Beat(133350, "Space"), new Beat(134050, "F"), new Beat(134060, "D"), new Beat(134740, "J"),
					new Beat(135450, "Space"), new Beat(136150, "Space"), new Beat(136810, "K"), new Beat(137630, "L"),
					new Beat(138250, "K"), new Beat(138880, "Space"), new Beat(139570, "S"), new Beat(140240, "F"),
					new Beat(140980, "D"), new Beat(141710, "K"), new Beat(141710, "J"), new Beat(142310, "F"),
					new Beat(143030, "Space"), new Beat(143750, "K"), new Beat(144470, "Space"), new Beat(145220, "K"),
					new Beat(145220, "L"), new Beat(145880, "Space"), new Beat(146610, "S"), new Beat(146610, "D"),
					new Beat(147260, "Space"), new Beat(147950, "J"), new Beat(148700, "D"), new Beat(149380, "Space"),
					new Beat(150020, "K"), new Beat(150760, "K"), new Beat(150780, "L"), new Beat(151430, "D"),
					new Beat(152230, "Space"), new Beat(152850, "K"), new Beat(152850, "J"), new Beat(153550, "Space"),
					new Beat(154220, "F"), new Beat(154920, "Space"), new Beat(155630, "K"), new Beat(155660, "J"),
					new Beat(156340, "Space"), new Beat(157030, "K"), new Beat(157030, "L"), new Beat(157750, "Space"),
					new Beat(158410, "K"), new Beat(158410, "J"), new Beat(159130, "Space"), new Beat(159830, "D"),
					new Beat(160500, "Space"), new Beat(161200, "K"), new Beat(161220, "J"), new Beat(161900, "F"),
					new Beat(162580, "Space"), new Beat(163300, "S"), new Beat(163980, "K"), new Beat(164000, "L"),
					new Beat(164680, "Space"), new Beat(165360, "J"), new Beat(166130, "K"), new Beat(166130, "J"),
					new Beat(166800, "Space"), new Beat(167530, "D"), new Beat(168160, "J"), new Beat(168930, "F"),
					new Beat(168950, "S"), new Beat(169550, "J"), new Beat(170270, "L"), new Beat(170270, "J"),
					new Beat(170950, "D"), new Beat(171650, "K"), new Beat(172400, "Space"), new Beat(173080, "Space"),
					new Beat(173760, "D"), new Beat(174480, "S"), new Beat(175180, "D"), new Beat(175930, "F"),
					new Beat(176580, "J"), new Beat(177960, "J"), new Beat(178650, "L"), new Beat(179310, "K"),
					new Beat(180700, "S"), new Beat(181460, "F"), new Beat(182100, "D"), new Beat(194130, "Space") };
		} else if (titleName.equals("Rollin_BraveGirls") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(1290, "L"), new Beat(1820, "S"), new Beat(2470, "L"), new Beat(3110, "S"),
					new Beat(3830, "K"), new Beat(4490, "D"), new Beat(5330, "K"), new Beat(5940, "D"),
					new Beat(6740, "F"), new Beat(7410, "L"), new Beat(8080, "S"), new Beat(8770, "L"),
					new Beat(9390, "D"), new Beat(10070, "K"), new Beat(10740, "D"), new Beat(11430, "K"),
					new Beat(12240, "S"), new Beat(12540, "D"), new Beat(12880, "F"), new Beat(13210, "Space"),
					new Beat(13530, "J"), new Beat(13870, "K"), new Beat(14260, "L"), new Beat(14570, "K"),
					new Beat(14960, "J"), new Beat(15310, "Space"), new Beat(15630, "F"), new Beat(15950, "D"),
					new Beat(16340, "S"), new Beat(16660, "D"), new Beat(17050, "F"), new Beat(17400, "Space"),
					new Beat(17650, "D"), new Beat(17980, "Space"), new Beat(18300, "J"), new Beat(18310, "L"),
					new Beat(18680, "D"), new Beat(19050, "Space"), new Beat(19430, "D"), new Beat(19730, "J"),
					new Beat(19750, "L"), new Beat(20060, "D"), new Beat(20460, "Space"), new Beat(20770, "D"),
					new Beat(21130, "L"), new Beat(21150, "J"), new Beat(21510, "S"), new Beat(21810, "F"),
					new Beat(22170, "S"), new Beat(22520, "L"), new Beat(22530, "J"), new Beat(22850, "S"),
					new Beat(23200, "F"), new Beat(23540, "D"), new Beat(23880, "J"), new Beat(23900, "K"),
					new Beat(24250, "D"), new Beat(24600, "Space"), new Beat(24910, "F"), new Beat(25230, "L"),
					new Beat(25250, "J"), new Beat(25620, "F"), new Beat(25940, "D"), new Beat(26270, "F"),
					new Beat(26620, "J"), new Beat(26620, "K"), new Beat(26970, "D"), new Beat(27350, "Space"),
					new Beat(27650, "S"), new Beat(28010, "F"), new Beat(28030, "S"), new Beat(28360, "D"),
					new Beat(28710, "F"), new Beat(29050, "D"), new Beat(29380, "L"), new Beat(29380, "J"),
					new Beat(29770, "S"), new Beat(30140, "F"), new Beat(30480, "D"), new Beat(30830, "J"),
					new Beat(30850, "K"), new Beat(31180, "D"), new Beat(31530, "Space"), new Beat(31900, "D"),
					new Beat(32250, "S"), new Beat(32260, "F"), new Beat(32610, "D"), new Beat(33160, "Space"),
					new Beat(33380, "F"), new Beat(33710, "K"), new Beat(33710, "J"), new Beat(34020, "D"),
					new Beat(34330, "F"), new Beat(34610, "S"), new Beat(34980, "K"), new Beat(35000, "L"),
					new Beat(35360, "D"), new Beat(35710, "F"), new Beat(36010, "S"), new Beat(36380, "Space"),
					new Beat(36450, "S"), new Beat(36730, "D"), new Beat(37120, "F"), new Beat(37460, "D"),
					new Beat(37780, "J"), new Beat(37800, "K"), new Beat(38150, "D"), new Beat(38560, "F"),
					new Beat(38880, "S"), new Beat(39250, "L"), new Beat(39550, "K"), new Beat(39910, "J"),
					new Beat(40260, "Space"), new Beat(40610, "D"), new Beat(40940, "F"), new Beat(41350, "Space"),
					new Beat(41650, "J"), new Beat(42040, "Space"), new Beat(42350, "J"), new Beat(42720, "K"),
					new Beat(43050, "L"), new Beat(43410, "K"), new Beat(43750, "L"), new Beat(44080, "K"),
					new Beat(44410, "J"), new Beat(44750, "S"), new Beat(45080, "D"), new Beat(45410, "Space"),
					new Beat(45730, "D"), new Beat(46100, "F"), new Beat(46500, "S"), new Beat(46830, "D"),
					new Beat(47180, "K"), new Beat(47200, "J"), new Beat(47580, "F"), new Beat(47900, "D"),
					new Beat(48230, "Space"), new Beat(48570, "D"), new Beat(48910, "S"), new Beat(49240, "F"),
					new Beat(49680, "S"), new Beat(49680, "D"), new Beat(50010, "D"), new Beat(50380, "F"),
					new Beat(50710, "D"), new Beat(51030, "K"), new Beat(51380, "J"), new Beat(51750, "J"),
					new Beat(51760, "L"), new Beat(52080, "K"), new Beat(52450, "J"), new Beat(52750, "D"),
					new Beat(53130, "F"), new Beat(53150, "S"), new Beat(53450, "D"), new Beat(53780, "Space"),
					new Beat(54100, "D"), new Beat(54460, "J"), new Beat(54500, "L"), new Beat(54880, "K"),
					new Beat(55230, "J"), new Beat(55530, "D"), new Beat(55900, "Space"), new Beat(56250, "D"),
					new Beat(56650, "F"), new Beat(56930, "D"), new Beat(57280, "S"), new Beat(57660, "F"),
					new Beat(58010, "J"), new Beat(58010, "L"), new Beat(58380, "K"), new Beat(58680, "D"),
					new Beat(59030, "Space"), new Beat(59380, "S"), new Beat(59410, "L"), new Beat(59930, "L"),
					new Beat(59970, "L"), new Beat(59980, "L"), new Beat(60200, "D"), new Beat(60200, "K"),
					new Beat(60880, "F"), new Beat(60900, "J"), new Beat(61560, "Space"), new Beat(62290, "F"),
					new Beat(62580, "D"), new Beat(62920, "L"), new Beat(62940, "J"), new Beat(63250, "S"),
					new Beat(63610, "K"), new Beat(63950, "D"), new Beat(64270, "L"), new Beat(64290, "J"),
					new Beat(64670, "F"), new Beat(65030, "D"), new Beat(65350, "K"), new Beat(65700, "L"),
					new Beat(65720, "J"), new Beat(66050, "K"), new Beat(66350, "J"), new Beat(66700, "L"),
					new Beat(67030, "Space"), new Beat(67450, "F"), new Beat(67450, "S"), new Beat(67800, "D"),
					new Beat(68110, "K"), new Beat(68380, "J"), new Beat(68800, "S"), new Beat(68800, "F"),
					new Beat(69150, "D"), new Beat(69530, "Space"), new Beat(69860, "L"), new Beat(69860, "J"),
					new Beat(70210, "D"), new Beat(70590, "Space"), new Beat(70870, "K"), new Beat(71250, "S"),
					new Beat(71270, "F"), new Beat(71580, "D"), new Beat(71970, "Space"), new Beat(72290, "J"),
					new Beat(72670, "K"), new Beat(73020, "J"), new Beat(73020, "L"), new Beat(73350, "D"),
					new Beat(73700, "Space"), new Beat(74070, "K"), new Beat(74380, "F"), new Beat(74400, "S"),
					new Beat(74770, "D"), new Beat(75120, "Space"), new Beat(75450, "L"), new Beat(75450, "J"),
					new Beat(75800, "K"), new Beat(76140, "J"), new Beat(76470, "D"), new Beat(76840, "Space"),
					new Beat(76850, "D"), new Beat(77250, "J"), new Beat(77550, "S"), new Beat(77900, "J"),
					new Beat(77900, "L"), new Beat(78270, "D"), new Beat(78580, "K"), new Beat(78950, "F"),
					new Beat(79290, "J"), new Beat(79620, "L"), new Beat(79640, "J"), new Beat(80000, "S"),
					new Beat(80370, "D"), new Beat(80680, "Space"), new Beat(81030, "J"), new Beat(81050, "K"),
					new Beat(81380, "Space"), new Beat(81720, "F"), new Beat(82030, "D"), new Beat(82450, "S"),
					new Beat(82460, "D"), new Beat(82480, "Space"), new Beat(82780, "D"), new Beat(83150, "F"),
					new Beat(83470, "L"), new Beat(83470, "J"), new Beat(83820, "K"), new Beat(84170, "J"),
					new Beat(84500, "L"), new Beat(84870, "K"), new Beat(85220, "S"), new Beat(85570, "D"),
					new Beat(85910, "F"), new Beat(86250, "Space"), new Beat(86580, "L"), new Beat(86930, "K"),
					new Beat(87300, "J"), new Beat(87620, "L"), new Beat(88000, "K"), new Beat(88380, "J"),
					new Beat(88690, "Space"), new Beat(89010, "F"), new Beat(89300, "D"), new Beat(89670, "S"),
					new Beat(90030, "S"), new Beat(90400, "D"), new Beat(90750, "F"), new Beat(91090, "Space"),
					new Beat(91440, "J"), new Beat(91770, "K"), new Beat(92120, "L"), new Beat(92470, "K"),
					new Beat(92880, "J"), new Beat(93230, "Space"), new Beat(93600, "L"), new Beat(93600, "J"),
					new Beat(93950, "S"), new Beat(93970, "F"), new Beat(94300, "D"), new Beat(94650, "K"),
					new Beat(95050, "Space"), new Beat(95050, "D"), new Beat(95370, "K"), new Beat(95710, "F"),
					new Beat(96070, "L"), new Beat(96090, "J"), new Beat(96450, "D"), new Beat(96750, "Space"),
					new Beat(97090, "F"), new Beat(97110, "S"), new Beat(97420, "D"), new Beat(97780, "Space"),
					new Beat(98090, "K"), new Beat(98450, "K"), new Beat(98470, "J"), new Beat(98810, "D"),
					new Beat(99130, "Space"), new Beat(99450, "F"), new Beat(99820, "S"), new Beat(99820, "F"),
					new Beat(100170, "Space"), new Beat(100470, "D"), new Beat(100870, "L"), new Beat(100870, "J"),
					new Beat(101200, "K"), new Beat(101550, "J"), new Beat(101920, "Space"), new Beat(101940, "F"),
					new Beat(102270, "D"), new Beat(102580, "F"), new Beat(102880, "S"), new Beat(103270, "J"),
					new Beat(103270, "K"), new Beat(103620, "D"), new Beat(103950, "Space"), new Beat(104290, "D"),
					new Beat(104630, "L"), new Beat(104650, "J"), new Beat(105040, "F"), new Beat(105050, "S"),
					new Beat(105390, "D"), new Beat(105720, "K"), new Beat(106090, "Space"), new Beat(106450, "K"),
					new Beat(106850, "D"), new Beat(107170, "J"), new Beat(107170, "K"), new Beat(107550, "F"),
					new Beat(107570, "S"), new Beat(107880, "D"), new Beat(108220, "L"), new Beat(108540, "K"),
					new Beat(108870, "K"), new Beat(108870, "J"), new Beat(109200, "D"), new Beat(109550, "Space"),
					new Beat(109880, "L"), new Beat(110250, "L"), new Beat(110270, "J"), new Beat(110650, "D"),
					new Beat(110990, "Space"), new Beat(111350, "S"), new Beat(111680, "J"), new Beat(111700, "K"),
					new Beat(112030, "D"), new Beat(112400, "Space"), new Beat(112710, "F"), new Beat(113120, "L"),
					new Beat(113140, "J"), new Beat(113170, "F"), new Beat(113170, "S"), new Beat(113470, "K"),
					new Beat(113800, "J"), new Beat(114140, "D"), new Beat(114470, "L"), new Beat(114810, "K"),
					new Beat(115150, "J"), new Beat(115470, "S"), new Beat(115850, "J"), new Beat(116240, "K"),
					new Beat(116600, "L"), new Beat(116980, "K"), new Beat(117320, "D"), new Beat(117640, "K"),
					new Beat(117970, "Space"), new Beat(118310, "J"), new Beat(118650, "K"), new Beat(118990, "L"),
					new Beat(119350, "S"), new Beat(119740, "K"), new Beat(120070, "L"), new Beat(120090, "J"),
					new Beat(120440, "D"), new Beat(120800, "Space"), new Beat(121110, "K"), new Beat(121450, "S"),
					new Beat(121800, "F"), new Beat(122150, "D"), new Beat(122470, "L"), new Beat(122840, "K"),
					new Beat(123190, "J"), new Beat(123570, "F"), new Beat(123920, "J"), new Beat(124270, "D"),
					new Beat(124580, "K"), new Beat(124970, "L"), new Beat(124980, "J"), new Beat(125320, "K"),
					new Beat(125670, "J"), new Beat(126020, "D"), new Beat(126340, "L"), new Beat(126350, "J"),
					new Beat(126700, "K"), new Beat(127020, "D"), new Beat(127380, "J"), new Beat(127690, "K"),
					new Beat(128050, "L"), new Beat(128440, "K"), new Beat(128790, "J"), new Beat(129150, "Space"),
					new Beat(129470, "D"), new Beat(129820, "S"), new Beat(130160, "D"), new Beat(130490, "K"),
					new Beat(130800, "D"), new Beat(131170, "L"), new Beat(131520, "K"), new Beat(131890, "J"),
					new Beat(132220, "Space"), new Beat(132590, "J"), new Beat(132940, "K"), new Beat(133290, "L"),
					new Beat(133590, "K"), new Beat(133950, "D"), new Beat(134320, "J"), new Beat(134330, "K"),
					new Beat(134710, "F"), new Beat(135060, "L"), new Beat(135390, "L"), new Beat(135410, "J"),
					new Beat(135790, "D"), new Beat(136120, "K"), new Beat(136420, "J"), new Beat(136790, "S"),
					new Beat(136790, "F"), new Beat(137140, "K"), new Beat(137490, "J"), new Beat(137790, "D"),
					new Beat(138140, "L"), new Beat(138160, "J"), new Beat(138520, "D"), new Beat(138870, "K"),
					new Beat(139270, "S"), new Beat(139270, "F"), new Beat(139610, "D"), new Beat(139960, "K"),
					new Beat(140310, "S"), new Beat(140320, "F"), new Beat(140340, "L"), new Beat(140360, "J"),
					new Beat(140640, "K"), new Beat(140960, "D"), new Beat(141270, "Space"), new Beat(141640, "F"),
					new Beat(141660, "J"), new Beat(141660, "S"), new Beat(141670, "L"), new Beat(141990, "Space"),
					new Beat(142350, "D"), new Beat(142670, "K"), new Beat(143060, "J"), new Beat(143070, "L"),
					new Beat(143410, "D"), new Beat(143760, "F"), new Beat(144060, "S"), new Beat(144420, "S"),
					new Beat(144440, "F"), new Beat(144790, "K"), new Beat(145110, "D"), new Beat(145440, "Space"),
					new Beat(145790, "J"), new Beat(146140, "K"), new Beat(146490, "L"), new Beat(146840, "K"),
					new Beat(147190, "F"), new Beat(147510, "D"), new Beat(147840, "S"), new Beat(148260, "Space"),
					new Beat(148620, "S"), new Beat(148640, "L"), new Beat(149340, "D"), new Beat(149360, "K"),
					new Beat(150040, "F"), new Beat(150060, "J"), new Beat(150790, "Space"), new Beat(151080, "F"),
					new Beat(151100, "J"), new Beat(151410, "D"), new Beat(151440, "K"), new Beat(151780, "L"),
					new Beat(152180, "K"), new Beat(152510, "J"), new Beat(152830, "Space"), new Beat(153180, "F"),
					new Beat(153490, "D"), new Beat(153820, "F"), new Beat(154190, "Space"), new Beat(154480, "D"),
					new Beat(154870, "F"), new Beat(155270, "Space"), new Beat(155590, "J"), new Beat(155980, "L"),
					new Beat(155980, "J"), new Beat(156320, "K"), new Beat(156660, "J"), new Beat(157000, "Space"),
					new Beat(157320, "F"), new Beat(157690, "F"), new Beat(157710, "S"), new Beat(158040, "D"),
					new Beat(158350, "F"), new Beat(158740, "Space"), new Beat(159070, "J"), new Beat(159090, "K"),
					new Beat(159420, "L"), new Beat(159760, "K"), new Beat(160110, "Space"), new Beat(160470, "J"),
					new Beat(160810, "D"), new Beat(161190, "K"), new Beat(161210, "L"), new Beat(161520, "S"),
					new Beat(161570, "D"), new Beat(161940, "L"), new Beat(162240, "K"), new Beat(162590, "D"),
					new Beat(162930, "F"), new Beat(163240, "Space"), new Beat(163590, "J"), new Beat(163930, "K"),
					new Beat(164260, "L"), new Beat(164610, "L"), new Beat(164610, "J"), new Beat(164990, "D"),
					new Beat(165310, "K"), new Beat(165670, "J"), new Beat(166020, "F"), new Beat(166040, "S"),
					new Beat(166360, "D"), new Beat(166770, "L"), new Beat(167080, "K"), new Beat(167440, "Space"),
					new Beat(167780, "J"), new Beat(168090, "K"), new Beat(168400, "L"), new Beat(168740, "K"),
					new Beat(169090, "J"), new Beat(169480, "L"), new Beat(169490, "J"), new Beat(169830, "D"),
					new Beat(170230, "K"), new Beat(170580, "F"), new Beat(170960, "Space"), new Beat(170960, "S"),
					new Beat(170980, "F"), new Beat(171740, "L"), new Beat(171760, "J"), new Beat(172380, "K"),
					new Beat(173070, "Space"), new Beat(173070, "D"), new Beat(173760, "S"), new Beat(174090, "D"),
					new Beat(174430, "F"), new Beat(174790, "J"), new Beat(175120, "L"), new Beat(175510, "K"),
					new Beat(175850, "J"), new Beat(176210, "Space"), new Beat(176550, "S"), new Beat(176890, "D"),
					new Beat(177240, "F"), new Beat(177590, "Space"), new Beat(177950, "J"), new Beat(178280, "L"),
					new Beat(178280, "J"), new Beat(178620, "K"), new Beat(179010, "J"), new Beat(179290, "L"),
					new Beat(179650, "K"), new Beat(180050, "J"), new Beat(180340, "Space"), new Beat(180640, "S"),
					new Beat(180660, "F"), new Beat(180960, "D"), new Beat(181330, "Space"), new Beat(181350, "D"),
					new Beat(181680, "F"), new Beat(182080, "D"), new Beat(182080, "J"), new Beat(182430, "K"),
					new Beat(182760, "L"), new Beat(183110, "K"), new Beat(183490, "J"), new Beat(183830, "S"),
					new Beat(184210, "D"), new Beat(184580, "F"), new Beat(184970, "Space"), new Beat(187280, "S"),
					new Beat(187310, "L"), new Beat(192670, "F"), new Beat(192690, "S"), new Beat(192700, "L"),
					new Beat(192710, "J"), new Beat(193660, "F"), new Beat(193680, "S"), new Beat(193680, "L"),
					new Beat(193700, "J"), };
		} else if (titleName.equals("Melody_Ash Island") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(6620, "S"), new Beat(7260, "L"), new Beat(7960, "S"), new Beat(8730, "L"),
					new Beat(9410, "Space"), new Beat(10100, "K"), new Beat(10780, "D"), new Beat(11520, "J"),
					new Beat(12230, "Space"), new Beat(12900, "L"), new Beat(13540, "S"), new Beat(14330, "J"),
					new Beat(15050, "Space"), new Beat(15720, "K"), new Beat(16380, "Space"), new Beat(17120, "F"),
					new Beat(18320, "Space"), new Beat(19040, "S"), new Beat(19680, "L"), new Beat(20400, "Space"),
					new Beat(21130, "S"), new Beat(21130, "D"), new Beat(21830, "Space"), new Beat(22520, "K"),
					new Beat(22520, "L"), new Beat(23210, "S"), new Beat(23900, "K"), new Beat(23900, "L"),
					new Beat(24580, "D"), new Beat(25290, "K"), new Beat(25300, "L"), new Beat(26040, "J"),
					new Beat(26710, "D"), new Beat(26730, "F"), new Beat(27440, "J"), new Beat(28100, "D"),
					new Beat(28120, "S"), new Beat(28840, "J"), new Beat(29550, "F"), new Beat(29560, "J"),
					new Beat(29560, "D"), new Beat(29580, "K"), new Beat(30240, "S"), new Beat(30930, "L"),
					new Beat(31630, "Space"), new Beat(32290, "K"), new Beat(33000, "D"), new Beat(33730, "K"),
					new Beat(33750, "L"), new Beat(34420, "D"), new Beat(35120, "K"), new Beat(35120, "J"),
					new Beat(35820, "Space"), new Beat(36500, "S"), new Beat(37170, "Space"), new Beat(37920, "J"),
					new Beat(37920, "K"), new Beat(38590, "D"), new Beat(39250, "Space"), new Beat(39250, "D"),
					new Beat(39970, "J"), new Beat(40690, "J"), new Beat(40700, "L"), new Beat(41350, "D"),
					new Beat(42050, "J"), new Beat(42080, "F"), new Beat(42080, "S"), new Beat(42100, "K"),
					new Beat(42800, "D"), new Beat(43510, "J"), new Beat(43510, "L"), new Beat(44170, "D"),
					new Beat(44860, "K"), new Beat(45520, "S"), new Beat(46260, "J"), new Beat(46960, "L"),
					new Beat(47640, "D"), new Beat(48320, "K"), new Beat(49050, "Space"), new Beat(49770, "J"),
					new Beat(50490, "S"), new Beat(50510, "D"), new Beat(51150, "Space"), new Beat(51820, "J"),
					new Beat(52540, "D"), new Beat(53190, "K"), new Beat(53200, "L"), new Beat(53870, "Space"),
					new Beat(54590, "K"), new Beat(55310, "D"), new Beat(55990, "J"), new Beat(56690, "S"),
					new Beat(56710, "D"), new Beat(57390, "Space"), new Beat(58070, "J"), new Beat(58780, "F"),
					new Beat(58800, "D"), new Beat(59440, "K"), new Beat(60170, "Space"), new Beat(60910, "J"),
					new Beat(61590, "F"), new Beat(61610, "D"), new Beat(62250, "K"), new Beat(62970, "Space"),
					new Beat(63690, "J"), new Beat(64340, "D"), new Beat(65060, "S"), new Beat(65760, "L"),
					new Beat(65760, "J"), new Beat(66420, "D"), new Beat(67130, "K"), new Beat(67840, "F"),
					new Beat(68570, "J"), new Beat(68580, "K"), new Beat(69240, "D"), new Beat(69920, "L"),
					new Beat(70620, "S"), new Beat(71390, "J"), new Beat(72040, "D"), new Beat(72700, "K"),
					new Beat(73370, "Space"), new Beat(74070, "J"), new Beat(74810, "S"), new Beat(75510, "L"),
					new Beat(76170, "D"), new Beat(76930, "K"), new Beat(76950, "J"), new Beat(77570, "D"),
					new Beat(78270, "K"), new Beat(78300, "L"), new Beat(78990, "D"), new Beat(79690, "F"),
					new Beat(80340, "D"), new Beat(81100, "Space"), new Beat(81130, "K"), new Beat(81130, "J"),
					new Beat(81790, "D"), new Beat(82440, "K"), new Beat(83170, "L"), new Beat(83870, "S"),
					new Beat(84540, "K"), new Beat(85220, "Space"), new Beat(85980, "J"), new Beat(86610, "D"),
					new Beat(87260, "L"), new Beat(88040, "K"), new Beat(88760, "J"), new Beat(89410, "S"),
					new Beat(90090, "D"), new Beat(90830, "F"), new Beat(91490, "L"), new Beat(92170, "Space"),
					new Beat(92870, "K"), new Beat(93570, "S"), new Beat(94290, "D"), new Beat(94970, "Space"),
					new Beat(95660, "S"), new Beat(96380, "Space"), new Beat(97060, "K"), new Beat(97760, "S"),
					new Beat(98510, "Space"), new Beat(99170, "L"), new Beat(99890, "Space"), new Beat(100550, "K"),
					new Beat(101240, "Space"), new Beat(101980, "J"), new Beat(102670, "Space"), new Beat(103360, "D"),
					new Beat(104020, "Space"), new Beat(104740, "L"), new Beat(105440, "Space"), new Beat(106110, "K"),
					new Beat(106910, "Space"), new Beat(107590, "S"), new Beat(108290, "Space"), new Beat(108970, "L"),
					new Beat(109630, "K"), new Beat(110370, "D"), new Beat(111040, "Space"), new Beat(111730, "S"),
					new Beat(112440, "Space"), new Beat(113160, "L"), new Beat(113840, "K"), new Beat(114560, "J"),
					new Beat(115210, "Space"), new Beat(115930, "D"), new Beat(116640, "Space"), new Beat(117300, "K"),
					new Beat(117990, "Space"), new Beat(118710, "L"), new Beat(119410, "Space"), new Beat(120100, "K"),
					new Beat(120800, "Space"), new Beat(121580, "S"), new Beat(122180, "Space"), new Beat(122830, "J"),
					new Beat(123630, "Space"), new Beat(124230, "K"), new Beat(125000, "Space"), new Beat(125760, "D"),
					new Beat(126380, "Space"), new Beat(127060, "J"), new Beat(127810, "Space"), new Beat(128480, "K"),
					new Beat(129180, "Space"), new Beat(129860, "L"), new Beat(130560, "Space"), new Beat(131260, "K"),
					new Beat(131280, "J"), new Beat(131940, "Space"), new Beat(132650, "S"), new Beat(132660, "D"),
					new Beat(133350, "Space"), new Beat(134050, "F"), new Beat(134060, "D"), new Beat(134740, "J"),
					new Beat(135450, "Space"), new Beat(136150, "Space"), new Beat(136810, "K"), new Beat(137630, "L"),
					new Beat(138250, "K"), new Beat(138880, "Space"), new Beat(139570, "S"), new Beat(140240, "F"),
					new Beat(140980, "D"), new Beat(141710, "K"), new Beat(141710, "J"), new Beat(142310, "F"),
					new Beat(143030, "Space"), new Beat(143750, "K"), new Beat(144470, "Space"), new Beat(145220, "K"),
					new Beat(145220, "L"), new Beat(145880, "Space"), new Beat(146610, "S"), new Beat(146610, "D"),
					new Beat(147260, "Space"), new Beat(147950, "J"), new Beat(148700, "D"), new Beat(149380, "Space"),
					new Beat(150020, "K"), new Beat(150760, "K"), new Beat(150780, "L"), new Beat(151430, "D"),
					new Beat(152230, "Space"), new Beat(152850, "K"), new Beat(152850, "J"), new Beat(153550, "Space"),
					new Beat(154220, "F"), new Beat(154920, "Space"), new Beat(155630, "K"), new Beat(155660, "J"),
					new Beat(156340, "Space"), new Beat(157030, "K"), new Beat(157030, "L"), new Beat(157750, "Space"),
					new Beat(158410, "K"), new Beat(158410, "J"), new Beat(159130, "Space"), new Beat(159830, "D"),
					new Beat(160500, "Space"), new Beat(161200, "K"), new Beat(161220, "J"), new Beat(161900, "F"),
					new Beat(162580, "Space"), new Beat(163300, "S"), new Beat(163980, "K"), new Beat(164000, "L"),
					new Beat(164680, "Space"), new Beat(165360, "J"), new Beat(166130, "K"), new Beat(166130, "J"),
					new Beat(166800, "Space"), new Beat(167530, "D"), new Beat(168160, "J"), new Beat(168930, "F"),
					new Beat(168950, "S"), new Beat(169550, "J"), new Beat(170270, "L"), new Beat(170270, "J"),
					new Beat(170950, "D"), new Beat(171650, "K"), new Beat(172400, "Space"), new Beat(173080, "Space"),
					new Beat(173760, "D"), new Beat(174480, "S"), new Beat(175180, "D"), new Beat(175930, "F"),
					new Beat(176580, "J"), new Beat(177960, "J"), new Beat(178650, "L"), new Beat(179310, "K"),
					new Beat(180700, "S"), new Beat(181460, "F"), new Beat(182100, "D") };
		} else if (titleName.equals("Melody_Ash Island") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(1290, "L"), new Beat(1820, "S"), new Beat(2470, "L"), new Beat(3110, "S"),
					new Beat(3830, "K"), new Beat(4490, "D"), new Beat(5330, "K"), new Beat(5940, "D"),
					new Beat(6740, "F"), new Beat(7410, "L"), new Beat(8080, "S"), new Beat(8770, "L"),
					new Beat(9390, "D"), new Beat(10070, "K"), new Beat(10740, "D"), new Beat(11430, "K"),
					new Beat(12240, "S"), new Beat(12540, "D"), new Beat(12880, "F"), new Beat(13210, "Space"),
					new Beat(13530, "J"), new Beat(13870, "K"), new Beat(14260, "L"), new Beat(14570, "K"),
					new Beat(14960, "J"), new Beat(15310, "Space"), new Beat(15630, "F"), new Beat(15950, "D"),
					new Beat(16340, "S"), new Beat(16660, "D"), new Beat(17050, "F"), new Beat(17400, "Space"),
					new Beat(17650, "D"), new Beat(17980, "Space"), new Beat(18300, "J"), new Beat(18310, "L"),
					new Beat(18680, "D"), new Beat(19050, "Space"), new Beat(19430, "D"), new Beat(19730, "J"),
					new Beat(19750, "L"), new Beat(20060, "D"), new Beat(20460, "Space"), new Beat(20770, "D"),
					new Beat(21130, "L"), new Beat(21150, "J"), new Beat(21510, "S"), new Beat(21810, "F"),
					new Beat(22170, "S"), new Beat(22520, "L"), new Beat(22530, "J"), new Beat(22850, "S"),
					new Beat(23200, "F"), new Beat(23540, "D"), new Beat(23880, "J"), new Beat(23900, "K"),
					new Beat(24250, "D"), new Beat(24600, "Space"), new Beat(24910, "F"), new Beat(25230, "L"),
					new Beat(25250, "J"), new Beat(25620, "F"), new Beat(25940, "D"), new Beat(26270, "F"),
					new Beat(26620, "J"), new Beat(26620, "K"), new Beat(26970, "D"), new Beat(27350, "Space"),
					new Beat(27650, "S"), new Beat(28010, "F"), new Beat(28030, "S"), new Beat(28360, "D"),
					new Beat(28710, "F"), new Beat(29050, "D"), new Beat(29380, "L"), new Beat(29380, "J"),
					new Beat(29770, "S"), new Beat(30140, "F"), new Beat(30480, "D"), new Beat(30830, "J"),
					new Beat(30850, "K"), new Beat(31180, "D"), new Beat(31530, "Space"), new Beat(31900, "D"),
					new Beat(32250, "S"), new Beat(32260, "F"), new Beat(32610, "D"), new Beat(33160, "Space"),
					new Beat(33380, "F"), new Beat(33710, "K"), new Beat(33710, "J"), new Beat(34020, "D"),
					new Beat(34330, "F"), new Beat(34610, "S"), new Beat(34980, "K"), new Beat(35000, "L"),
					new Beat(35360, "D"), new Beat(35710, "F"), new Beat(36010, "S"), new Beat(36380, "Space"),
					new Beat(36450, "S"), new Beat(36730, "D"), new Beat(37120, "F"), new Beat(37460, "D"),
					new Beat(37780, "J"), new Beat(37800, "K"), new Beat(38150, "D"), new Beat(38560, "F"),
					new Beat(38880, "S"), new Beat(39250, "L"), new Beat(39550, "K"), new Beat(39910, "J"),
					new Beat(40260, "Space"), new Beat(40610, "D"), new Beat(40940, "F"), new Beat(41350, "Space"),
					new Beat(41650, "J"), new Beat(42040, "Space"), new Beat(42350, "J"), new Beat(42720, "K"),
					new Beat(43050, "L"), new Beat(43410, "K"), new Beat(43750, "L"), new Beat(44080, "K"),
					new Beat(44410, "J"), new Beat(44750, "S"), new Beat(45080, "D"), new Beat(45410, "Space"),
					new Beat(45730, "D"), new Beat(46100, "F"), new Beat(46500, "S"), new Beat(46830, "D"),
					new Beat(47180, "K"), new Beat(47200, "J"), new Beat(47580, "F"), new Beat(47900, "D"),
					new Beat(48230, "Space"), new Beat(48570, "D"), new Beat(48910, "S"), new Beat(49240, "F"),
					new Beat(49680, "S"), new Beat(49680, "D"), new Beat(50010, "D"), new Beat(50380, "F"),
					new Beat(50710, "D"), new Beat(51030, "K"), new Beat(51380, "J"), new Beat(51750, "J"),
					new Beat(51760, "L"), new Beat(52080, "K"), new Beat(52450, "J"), new Beat(52750, "D"),
					new Beat(53130, "F"), new Beat(53150, "S"), new Beat(53450, "D"), new Beat(53780, "Space"),
					new Beat(54100, "D"), new Beat(54460, "J"), new Beat(54500, "L"), new Beat(54880, "K"),
					new Beat(55230, "J"), new Beat(55530, "D"), new Beat(55900, "Space"), new Beat(56250, "D"),
					new Beat(56650, "F"), new Beat(56930, "D"), new Beat(57280, "S"), new Beat(57660, "F"),
					new Beat(58010, "J"), new Beat(58010, "L"), new Beat(58380, "K"), new Beat(58680, "D"),
					new Beat(59030, "Space"), new Beat(59380, "S"), new Beat(59410, "L"), new Beat(59930, "L"),
					new Beat(59970, "L"), new Beat(59980, "L"), new Beat(60200, "D"), new Beat(60200, "K"),
					new Beat(60880, "F"), new Beat(60900, "J"), new Beat(61560, "Space"), new Beat(62290, "F"),
					new Beat(62580, "D"), new Beat(62920, "L"), new Beat(62940, "J"), new Beat(63250, "S"),
					new Beat(63610, "K"), new Beat(63950, "D"), new Beat(64270, "L"), new Beat(64290, "J"),
					new Beat(64670, "F"), new Beat(65030, "D"), new Beat(65350, "K"), new Beat(65700, "L"),
					new Beat(65720, "J"), new Beat(66050, "K"), new Beat(66350, "J"), new Beat(66700, "L"),
					new Beat(67030, "Space"), new Beat(67450, "F"), new Beat(67450, "S"), new Beat(67800, "D"),
					new Beat(68110, "K"), new Beat(68380, "J"), new Beat(68800, "S"), new Beat(68800, "F"),
					new Beat(69150, "D"), new Beat(69530, "Space"), new Beat(69860, "L"), new Beat(69860, "J"),
					new Beat(70210, "D"), new Beat(70590, "Space"), new Beat(70870, "K"), new Beat(71250, "S"),
					new Beat(71270, "F"), new Beat(71580, "D"), new Beat(71970, "Space"), new Beat(72290, "J"),
					new Beat(72670, "K"), new Beat(73020, "J"), new Beat(73020, "L"), new Beat(73350, "D"),
					new Beat(73700, "Space"), new Beat(74070, "K"), new Beat(74380, "F"), new Beat(74400, "S"),
					new Beat(74770, "D"), new Beat(75120, "Space"), new Beat(75450, "L"), new Beat(75450, "J"),
					new Beat(75800, "K"), new Beat(76140, "J"), new Beat(76470, "D"), new Beat(76840, "Space"),
					new Beat(76850, "D"), new Beat(77250, "J"), new Beat(77550, "S"), new Beat(77900, "J"),
					new Beat(77900, "L"), new Beat(78270, "D"), new Beat(78580, "K"), new Beat(78950, "F"),
					new Beat(79290, "J"), new Beat(79620, "L"), new Beat(79640, "J"), new Beat(80000, "S"),
					new Beat(80370, "D"), new Beat(80680, "Space"), new Beat(81030, "J"), new Beat(81050, "K"),
					new Beat(81380, "Space"), new Beat(81720, "F"), new Beat(82030, "D"), new Beat(82450, "S"),
					new Beat(82460, "D"), new Beat(82480, "Space"), new Beat(82780, "D"), new Beat(83150, "F"),
					new Beat(83470, "L"), new Beat(83470, "J"), new Beat(83820, "K"), new Beat(84170, "J"),
					new Beat(84500, "L"), new Beat(84870, "K"), new Beat(85220, "S"), new Beat(85570, "D"),
					new Beat(85910, "F"), new Beat(86250, "Space"), new Beat(86580, "L"), new Beat(86930, "K"),
					new Beat(87300, "J"), new Beat(87620, "L"), new Beat(88000, "K"), new Beat(88380, "J"),
					new Beat(88690, "Space"), new Beat(89010, "F"), new Beat(89300, "D"), new Beat(89670, "S"),
					new Beat(90030, "S"), new Beat(90400, "D"), new Beat(90750, "F"), new Beat(91090, "Space"),
					new Beat(91440, "J"), new Beat(91770, "K"), new Beat(92120, "L"), new Beat(92470, "K"),
					new Beat(92880, "J"), new Beat(93230, "Space"), new Beat(93600, "L"), new Beat(93600, "J"),
					new Beat(93950, "S"), new Beat(93970, "F"), new Beat(94300, "D"), new Beat(94650, "K"),
					new Beat(95050, "Space"), new Beat(95050, "D"), new Beat(95370, "K"), new Beat(95710, "F"),
					new Beat(96070, "L"), new Beat(96090, "J"), new Beat(96450, "D"), new Beat(96750, "Space"),
					new Beat(97090, "F"), new Beat(97110, "S"), new Beat(97420, "D"), new Beat(97780, "Space"),
					new Beat(98090, "K"), new Beat(98450, "K"), new Beat(98470, "J"), new Beat(98810, "D"),
					new Beat(99130, "Space"), new Beat(99450, "F"), new Beat(99820, "S"), new Beat(99820, "F"),
					new Beat(100170, "Space"), new Beat(100470, "D"), new Beat(100870, "L"), new Beat(100870, "J"),
					new Beat(101200, "K"), new Beat(101550, "J"), new Beat(101920, "Space"), new Beat(101940, "F"),
					new Beat(102270, "D"), new Beat(102580, "F"), new Beat(102880, "S"), new Beat(103270, "J"),
					new Beat(103270, "K"), new Beat(103620, "D"), new Beat(103950, "Space"), new Beat(104290, "D"),
					new Beat(104630, "L"), new Beat(104650, "J"), new Beat(105040, "F"), new Beat(105050, "S"),
					new Beat(105390, "D"), new Beat(105720, "K"), new Beat(106090, "Space"), new Beat(106450, "K"),
					new Beat(106850, "D"), new Beat(107170, "J"), new Beat(107170, "K"), new Beat(107550, "F"),
					new Beat(107570, "S"), new Beat(107880, "D"), new Beat(108220, "L"), new Beat(108540, "K"),
					new Beat(108870, "K"), new Beat(108870, "J"), new Beat(109200, "D"), new Beat(109550, "Space"),
					new Beat(109880, "L"), new Beat(110250, "L"), new Beat(110270, "J"), new Beat(110650, "D"),
					new Beat(110990, "Space"), new Beat(111350, "S"), new Beat(111680, "J"), new Beat(111700, "K"),
					new Beat(112030, "D"), new Beat(112400, "Space"), new Beat(112710, "F"), new Beat(113120, "L"),
					new Beat(113140, "J"), new Beat(113170, "F"), new Beat(113170, "S"), new Beat(113470, "K"),
					new Beat(113800, "J"), new Beat(114140, "D"), new Beat(114470, "L"), new Beat(114810, "K"),
					new Beat(115150, "J"), new Beat(115470, "S"), new Beat(115850, "J"), new Beat(116240, "K"),
					new Beat(116600, "L"), new Beat(116980, "K"), new Beat(117320, "D"), new Beat(117640, "K"),
					new Beat(117970, "Space"), new Beat(118310, "J"), new Beat(118650, "K"), new Beat(118990, "L"),
					new Beat(119350, "S"), new Beat(119740, "K"), new Beat(120070, "L"), new Beat(120090, "J"),
					new Beat(120440, "D"), new Beat(120800, "Space"), new Beat(121110, "K"), new Beat(121450, "S"),
					new Beat(121800, "F"), new Beat(122150, "D"), new Beat(122470, "L"), new Beat(122840, "K"),
					new Beat(123190, "J"), new Beat(123570, "F"), new Beat(123920, "J"), new Beat(124270, "D"),
					new Beat(124580, "K"), new Beat(124970, "L"), new Beat(124980, "J"), new Beat(125320, "K"),
					new Beat(125670, "J"), new Beat(126020, "D"), new Beat(126340, "L"), new Beat(126350, "J"),
					new Beat(126700, "K"), new Beat(127020, "D"), new Beat(127380, "J"), new Beat(127690, "K"),
					new Beat(128050, "L"), new Beat(128440, "K"), new Beat(128790, "J"), new Beat(129150, "Space"),
					new Beat(129470, "D"), new Beat(129820, "S"), new Beat(130160, "D"), new Beat(130490, "K"),
					new Beat(130800, "D"), new Beat(131170, "L"), new Beat(131520, "K"), new Beat(131890, "J"),
					new Beat(132220, "Space"), new Beat(132590, "J"), new Beat(132940, "K"), new Beat(133290, "L"),
					new Beat(133590, "K"), new Beat(133950, "D"), new Beat(134320, "J"), new Beat(134330, "K"),
					new Beat(134710, "F"), new Beat(135060, "L"), new Beat(135390, "L"), new Beat(135410, "J"),
					new Beat(135790, "D"), new Beat(136120, "K"), new Beat(136420, "J"), new Beat(136790, "S"),
					new Beat(136790, "F"), new Beat(137140, "K"), new Beat(137490, "J"), new Beat(137790, "D"),
					new Beat(138140, "L"), new Beat(138160, "J"), new Beat(138520, "D"), new Beat(138870, "K"),
					new Beat(139270, "S"), new Beat(139270, "F"), new Beat(139610, "D"), new Beat(139960, "K"),
					new Beat(140310, "S"), new Beat(140320, "F"), new Beat(140340, "L"), new Beat(140360, "J"),
					new Beat(140640, "K"), new Beat(140960, "D"), new Beat(141270, "Space"), new Beat(141640, "F"),
					new Beat(141660, "J"), new Beat(141660, "S"), new Beat(141670, "L"), new Beat(141990, "Space"),
					new Beat(142350, "D"), new Beat(142670, "K"), new Beat(143060, "J"), new Beat(143070, "L"),
					new Beat(143410, "D"), new Beat(143760, "F"), new Beat(144060, "S"), new Beat(144420, "S"),
					new Beat(144440, "F"), new Beat(144790, "K"), new Beat(145110, "D"), new Beat(145440, "Space"),
					new Beat(145790, "J"), new Beat(146140, "K"), new Beat(146490, "L"), new Beat(146840, "K"),
					new Beat(147190, "F"), new Beat(147510, "D"), new Beat(147840, "S"), new Beat(148260, "Space"),
					new Beat(148620, "S"), new Beat(148640, "L"), new Beat(149340, "D"), new Beat(149360, "K"),
					new Beat(150040, "F"), new Beat(150060, "J"), new Beat(150790, "Space"), new Beat(151080, "F"),
					new Beat(151100, "J"), new Beat(151410, "D"), new Beat(151440, "K"), new Beat(151780, "L"),
					new Beat(152180, "K"), new Beat(152510, "J"), new Beat(152830, "Space"), new Beat(153180, "F"),
					new Beat(153490, "D"), new Beat(153820, "F"), new Beat(154190, "Space"), new Beat(154480, "D"),
					new Beat(154870, "F"), new Beat(155270, "Space"), new Beat(155590, "J"), new Beat(155980, "L"),
					new Beat(155980, "J"), new Beat(156320, "K"), new Beat(156660, "J"), new Beat(157000, "Space"),
					new Beat(157320, "F"), new Beat(157690, "F"), new Beat(157710, "S"), new Beat(158040, "D"),
					new Beat(158350, "F"), new Beat(158740, "Space"), new Beat(159070, "J"), new Beat(159090, "K"),
					new Beat(159420, "L"), new Beat(159760, "K"), new Beat(160110, "Space"), new Beat(160470, "J"),
					new Beat(160810, "D"), new Beat(161190, "K"), new Beat(161210, "L"), new Beat(161520, "S"),
					new Beat(161570, "D"), new Beat(161940, "L"), new Beat(162240, "K"), new Beat(162590, "D"),
					new Beat(162930, "F"), new Beat(163240, "Space"), new Beat(163590, "J"), new Beat(163930, "K"),
					new Beat(164260, "L"), new Beat(164610, "L"), new Beat(164610, "J"), new Beat(164990, "D"),
					new Beat(165310, "K"), new Beat(165670, "J"), new Beat(166020, "F"), new Beat(166040, "S"),
					new Beat(166360, "D"), new Beat(166770, "L"), new Beat(167080, "K"), new Beat(167440, "Space"),
					new Beat(167780, "J"), new Beat(168090, "K"), new Beat(168400, "L"), new Beat(168740, "K"),
					new Beat(169090, "J"), new Beat(169480, "L"), new Beat(169490, "J"), new Beat(169830, "D"),
					new Beat(170230, "K"), new Beat(170580, "F"), new Beat(170960, "Space"), new Beat(170960, "S"),
					new Beat(170980, "F"), new Beat(171740, "L"), new Beat(171760, "J"), new Beat(172380, "K"),
					new Beat(173070, "Space"), new Beat(173070, "D"), new Beat(173760, "S"), new Beat(174090, "D"),
					new Beat(174430, "F"), new Beat(174790, "J"), new Beat(175120, "L"), new Beat(175510, "K"),
					new Beat(175850, "J"), new Beat(176210, "Space"), new Beat(176550, "S"), new Beat(176890, "D"),
					new Beat(177240, "F"), new Beat(177590, "Space"), new Beat(177950, "J"), new Beat(178280, "L"),
					new Beat(178280, "J"), new Beat(178620, "K"), new Beat(179010, "J"), new Beat(179290, "L"),
					new Beat(179650, "K"), new Beat(180050, "J"), new Beat(180340, "Space"), new Beat(180640, "S"),
					new Beat(180660, "F"), new Beat(180960, "D"), new Beat(181330, "Space"), new Beat(181350, "D"),
					new Beat(181680, "F"), new Beat(182080, "D"), new Beat(182080, "J"), new Beat(182430, "K"),
					new Beat(182760, "L"), new Beat(183110, "K"), new Beat(183490, "J"), new Beat(183830, "S"),
					new Beat(184210, "D"), new Beat(184580, "F"), new Beat(184970, "Space"), new Beat(187280, "S"),
					new Beat(187310, "L"), };
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName(), score);
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					// e.printStackTrace(); -> 이걸로 하면 오류뜨고 첫번째 곡이 처음에 입력안됨
				}
			}
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public Score getScore() {
		return score;
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}

	}
}
