package game;

import game.entities.PacGum;
import game.entities.SuperPacGum;
import game.entities.ghosts.Ghost;


public interface Sujet {
	/**
	 * 
	 * @param observer
	 *  định nghĩa các phương thức để quản lý danh sách các đối tượng Observer
	 *  đăng ký để nhận thông báo khi có sự kiện xảy ra
	 */
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserverPacGumEaten(PacGum pg);
    void notifyObserverSuperPacGumEaten(SuperPacGum spg);
    void notifyObserverGhostCollision(Ghost gh);
}
