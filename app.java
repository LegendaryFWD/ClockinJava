import java.awt.*;
import java.awt.image.BufferStrategy;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class app extends Canvas implements Runnable{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;

    LocalDate dt = LocalDate.now(); 
    LocalTime tm = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Font ft = new Font("DS-DIGITAL", Font.PLAIN, 125);
    Font ftSmol = new Font("CozetteVector", Font.PLAIN, 25);

    
    public app(){
        new display(WIDTH, HEIGHT, "Relógio", this);
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
        soundhndl.loopSound("alarm.wav");
        run();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        long LastUpd = System.nanoTime();
        final double ns = 1000000000.0 / 60; 
        double delta = 0;
        while(running){
            long ag = System.nanoTime();
            delta += (ag - LastUpd) / ns;
            LastUpd = ag;
            while(delta  >= 1){
                update();
                delta--;
                render();
            }
        }
        stop();
    }

    private void render(){
        BufferStrategy b = this.getBufferStrategy();
        if(b == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = b.getDrawGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setFont(ft);

        g.setColor(Color.RED);

        g.drawString(formatter.format(tm), 190, 400);
        g.setFont(ftSmol);
        g.setColor(Color.WHITE);
        g.drawString(formatterDate.format(dt), 25, 750);
        
        
        g.dispose();
        b.show();
    }
    
    
    private void update(){
        dt = LocalDate.now();
        tm = LocalTime.now();
    }
    
    public static void main(String[] args) {
        new app();
        
    }
}