package game.ghostStates;

import game.entities.ghosts.Ghost;
import game.utils.Utils;
import game.utils.WallCollisionDetector;

public abstract class GhostState {
	protected Ghost ghost;

	public GhostState(Ghost ghost) {
		this.ghost = ghost;
	}

	public void superPacGumEaten() {
	}

	public void timerModeOver() {
	}

	public void timerFrightenedModeOver() {
	}

	public void eaten() {
	}

	public void outsideHouse() {
	}

	public void insideHouse() {
	}

	public int[] getTargetPosition() {
		return new int[2];
	}

	public void computeNextDir() {
		int new_xSpd = 0;
		int new_ySpd = 0;

		if (!ghost.onTheGrid())
			return;
		if (!ghost.onGameplayWindow())
			return;

		double minDist = Double.MAX_VALUE;
		if (ghost.getxSpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, -ghost.getSpd(), 0)) {
			
			double distance = Utils.getDistance(ghost.getxPos() - ghost.getSpd(), ghost.getyPos(),
					getTargetPosition()[0], getTargetPosition()[1]);


			if (distance < minDist) {
				new_xSpd = -ghost.getSpd();
				new_ySpd = 0;
				minDist = distance;
			}
		}


		if (ghost.getxSpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, ghost.getSpd(), 0)) {
			double distance = Utils.getDistance(ghost.getxPos() + ghost.getSpd(), ghost.getyPos(),
					getTargetPosition()[0], getTargetPosition()[1]);
			if (distance < minDist) {
				new_xSpd = ghost.getSpd();
				new_ySpd = 0;
				minDist = distance;
			}
		}

		if (ghost.getySpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, -ghost.getSpd())) {
			double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getSpd(),
					getTargetPosition()[0], getTargetPosition()[1]);
			if (distance < minDist) {
				new_xSpd = 0;
				new_ySpd = -ghost.getSpd();
				minDist = distance;
			}
		}

		if (ghost.getySpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, ghost.getSpd())) {
			double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getSpd(),
					getTargetPosition()[0], getTargetPosition()[1]);
			if (distance < minDist) {
				new_xSpd = 0;
				new_ySpd = ghost.getSpd();
				minDist = distance;
			}
		}

		if (new_xSpd == 0 && new_ySpd == 0)
			return;


		if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
			ghost.setxSpd(new_xSpd);
			ghost.setySpd(new_ySpd);
		} else {
			if (new_xSpd != 0) {
				ghost.setxSpd(0);
				ghost.setxSpd(new_ySpd);
			} else {
				ghost.setxSpd(new_xSpd);
				ghost.setySpd(0);
			}
		}
	}
}
