package commands;

import main.ChessBoard;

public class ResignCommand extends Command{

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ChessBoard b = ChessBoard.getInstance();
		if(b.isPlayingColor())
			System.out.println("0-1 {White resigns}");
		else
			System.out.println("0-1 {Black resigns}");
		//??b.qiut
	}

}
