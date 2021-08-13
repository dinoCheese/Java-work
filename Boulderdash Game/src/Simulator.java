class Simulator extends Thread {
    private World world;
    private GUI gui;
    private int frameDelay;

    Simulator(World world, GUI gui) {
        frameDelay = 500; // milliseconds
        this.world = world;
        this.gui = gui;
    }

    // Simulates the world
    public void run() {
        int outcome = 0;
        while (true) {
            // sleep for some frame delay
            try {
                Thread.sleep(frameDelay);
            }
            catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }

            // now change the world

            boolean deathByMonster;
            deathByMonster = world.moveMonsters();
            System.out.println("Still going");

            if (deathByMonster) {
                outcome = 2;
                break;
            }

            boolean deathByGravity = world.applyGravity(-1, -1);
            if (deathByGravity) {
                outcome = 2;
                break;
            }

            // now redraw the world
            gui.redraw(world);
        }

        // add code: print message based on outcome
        System.exit(-1);
    }
}
