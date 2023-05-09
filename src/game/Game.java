package game;

import game.entities.*;  
import game.entities.ghosts.Blinky;
import game.entities.ghosts.Ghost;
import game.ghostFactory.*;
import game.ghostStates.EatenMode;
import game.ghostStates.FrightenedMode;
import game.utils.CollisionDetector;
import game.utils.CsvReader;
import game.utils.KeyHandler;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class Game implements Observer {
    private List<Entity> objects = new ArrayList();
    private List<Ghost> ghosts = new ArrayList();
    private static List<Wall> walls = new ArrayList();

    private static Pacman pacman;
    private static Blinky blinky;

    private static boolean firstInput = false;

    public Game(){
        List<List<String>> data = null;
        try {
        	//đọc tập tin csv
            data = new CsvReader().parseCsv(getClass().getClassLoader().getResource("resources/level/level.csv").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        int cellsPerRow = data.get(0).size();//sl ô mỗi hàng
        int cellsPerColumn = data.size();//sl ô mỗi cột
        int cellSize = 8;//kích thước ô

        CollisionDetector collisionDetector = new CollisionDetector(this);//xác định va chạm của các đối tượng trong game
        AbstractGhostFactory abstractGhostFactory = null;

        for(int xx = 0 ; xx < cellsPerRow ; xx++) {
            for(int yy = 0 ; yy < cellsPerColumn ; yy++) {
            	//xx đại diện cho vị trí cột
            	//yy đại diện cho vị trí hàng
                String dataChar = data.get(yy).get(xx);
                if (dataChar.equals("x")) {
                	//nếu giá trị là x thì tạo 1 wall
                    objects.add(new Wall(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("P")) {
                	//nếu giá trị là p thì tạo 1 pacman
                    pacman = new Pacman(xx * cellSize, yy * cellSize);
                    pacman.setCollisionDetector(collisionDetector);
                    
                    pacman.registerObserver(GameLauncher.getUIPanel());
                    pacman.registerObserver(this);
                }else if (dataChar.equals("b") || dataChar.equals("p") || dataChar.equals("i") || dataChar.equals("c")) { //CrÃ©ation des fantÃ´mes en utilisant les diffÃ©rentes factories
                    switch (dataChar) {
                    	//tạo đối tượng ghost tương ứng
                        case "b":
                            abstractGhostFactory = new BlinkyFactory();
                            break;
                        case "p":
                            abstractGhostFactory = new PinkyFactory();
                            break;
                        case "i":
                            abstractGhostFactory = new InkyFactory();
                            break;
                        case "c":
                            abstractGhostFactory = new ClydeFactory();
                            break;
                    }

                    Ghost ghost = abstractGhostFactory.makeGhost(xx * cellSize, yy * cellSize);
                    ghosts.add(ghost);
                    if (dataChar.equals("b")) {
                        blinky = (Blinky) ghost;
                    }
                }else if (dataChar.equals(".")) {
                	//tạo đối tượng pacgum
                    objects.add(new PacGum(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("o")) {
                	//tạo đối tượng super pacgum
                    objects.add(new SuperPacGum(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("-")) {
                	//tạo đối tượng ghosthouse
                    objects.add(new GhostHouse(xx * cellSize, yy * cellSize));
                }
            }
        }
        objects.add(pacman);
        objects.addAll(ghosts);

        for (Entity o : objects) {
            if (o instanceof Wall) {
                walls.add((Wall) o);
            }
        }
    }

    public static List<Wall> getWalls() {
        return walls;
    }

    public List<Entity> getEntities() {
        return objects;
    }


    public void update() {
        for (Entity o: objects) {
            if (!o.isDestroyed()) o.update();
        }
    }

    public void input(KeyHandler k) {
        pacman.input(k);
    }

    public void render(Graphics2D g) {
        for (Entity o: objects) {
            if (!o.isDestroyed()) o.render(g);
        }
    }

    public static Pacman getPacman() {
        return pacman;
    }
    public static Blinky getBlinky() {
        return blinky;
    }

    @Override
    public void updatePacGumEaten(PacGum pg) {
        pg.destroy();
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
    	// phương thức sẽ xóa đối tượng SuperPacGum khỏi trò chơi và đặt trạng thái cho tất cả các Ghost là 'frightened'
        spg.destroy();
        for (Ghost gh : ghosts) {
            gh.getState().superPacGumEaten();
        }
    }

    @Override
    public void updateGhostCollision(Ghost gh) {
    	//nếu pacman va chạm với ghost
    	//kiểm tra xem ghost là FrightenedMode hay là EatenMode
        if (gh.getState() instanceof FrightenedMode) {
        	//thực hiện phương thức eaten()
            gh.getState().eaten();
        }else if (!(gh.getState() instanceof EatenMode)) {
        	//trò chơi kết thúc
            System.out.println("Game over !\nScore : " + GameLauncher.getUIPanel().getScore());
            System.exit(0); //TODO
        }
    }

    public static void setFirstInput(boolean b) {
        firstInput = b;
    }

    public static boolean getFirstInput() {
        return firstInput;
    }
}
