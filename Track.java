package dynamic_beat_final;

public class Track {

	private String titleImage;	//제목 부분 이미지
	private String startImage;	//게임 선택 창 표시 이미지
	private String gameImage;	//해당 곡을 실행했을 때 표지 이미지
	private String startMusic;	//게임 선택 창 음악
	private String gameMusic;	//해당 곡을 실행했을 때 음악
	private String titleName;	//곡 제목
	private int playtime;
	
	public Track(String titleImage, String startImage, String playImage, String startMusic, String gameMusic,
			String titleName, int playtime) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = playImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
		this.playtime = playtime;
	}

	
	// getter, setter
	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	
	
}
