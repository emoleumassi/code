package de.emo.cit.tuberlin;

public class Track {

	String title;
	String singer;
	private Emo emo;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Emo getEmo() {
		return emo;
	}

	public void setEmo(Emo emo) {
		this.emo = emo;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + ", emo=" + emo
				+ "]";
	}
}
