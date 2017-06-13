package sokobanAdapter.state;

import state.State;

public class SokoState extends State<char[][]> {
	
	public SokoState(char[][] state) {
		super(state);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(char[] cArr : (char[][])getState())
		{
			for(char c : cArr)
				sb.append(c);
			sb.append("\r\n");
		}	
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}