package jonjar.ftg.manager;

import jonjar.ftg.FTG;
import jonjar.ftg.entity.PlayerInfo;
import jonjar.ftg.util.MsgSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager extends MsgSender {
    
    private final FTG plugin;
    public GameManager(FTG main){
        this.plugin = main;
    }

    private int elapsed_sec;
    public static boolean PAUSE = false;

    private GameManagerTask runnable;

    /*
    1. 관리자가 team 명령어를 통해 팀 배분 시켰는지 확인
    2.
     */

    public void start(Player p){
        if(runnable != null && !runnable.isCancelled()){
            error(p, "이미 게임이 시작되어있습니다.");
        } else {
            reset();
            runnable = new GameManagerTask();
            runnable.runTaskTimer(plugin, 0L, 20L);
            broadcast("§f§l" + p.getName() + "님께서 게임을 시작하셨습니다!");
        }
    }

    public void stop(Player p){
        if(runnable != null && runnable.isCancelled()){
            error(p, "게임이 종료되어있습니다.");
        } else {

        }
    }
    
    public void pause(Player p){
        if(runnable == null || runnable.isCancelled())
            error(p, "§c게임이 시작되어있지 않습니다.");
        else {
            PAUSE = !PAUSE;
            broadcast(PAUSE ? "§c§l" + p.getName() + "님께서 게임을 일시정지하셨습니다." : "§f§l" + p.getName() + "님께서 게임을 재개하셨습니다!");
        }
    }

    private boolean checkTeam(){

        for(Player ap : Bukkit.getOnlinePlayers()){
            PlayerInfo pi = PlayerInfo.getPlayerInfo(ap);
            if(pi.getTeam() == null && !pi.isObserver())
                return false;
        }

        return true;
    }
    
    

    private void reset(){
        this.elapsed_sec = 0;
    }


    public class GameManagerTask extends BukkitRunnable {

        public void run(){
            
            if(PAUSE)
                return;
            
            switch(elapsed_sec){
                case 1:

                    break;
            }
        }

    }
}
