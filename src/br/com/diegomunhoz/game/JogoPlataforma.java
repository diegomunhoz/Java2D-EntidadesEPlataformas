package br.com.diegomunhoz.game;

import br.com.diegomunhoz.core.AudioManager;
import br.com.diegomunhoz.core.Game;
import br.com.diegomunhoz.core.InputManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class JogoPlataforma extends Game {

    // Modelo do jogo.
    CollisionDetector collisionDetector;
    ArrayList<Entidade> entidades;
    boolean gameOver;

    public JogoPlataforma() {
        entidades = new ArrayList<Entidade>();
        collisionDetector = new CollisionDetector(entidades);
        gameOver = false;
    }

    @Override
    public void onLoad() {
        try {
            AudioManager.getInstance().loadAudio("chilla_dream_pad_1_130bpm.wav").loop();
            // Inicializa a fase.
            // Inclui o objeto do jogador (que inicia na posição (300,300) e recebe o inputPool)
            entidades.add(new EntidadeJogador(300, 300));
            // Em seguida insere vários objetos do tipo bloco, que serão as plataformas.
            entidades.add(new EntidadePlataforma(0, 550, 600, 20));
            entidades.add(new EntidadePlataforma(0, 200, 20, 350));
            entidades.add(new EntidadePlataforma(700, 450, 100, 20));
            entidades.add(new EntidadePlataforma(780, 400, 20, 50));
            entidades.add(new EntidadePlataforma(550, 350, 50, 10));
            entidades.add(new EntidadePlataforma(400, 250, 50, 10));
            entidades.add(new EntidadePlataforma(250, 150, 50, 10));
            entidades.add(new EntidadePlataforma(0, 200, 150, 20));
            entidades.add(new EntidadePlataforma(0, 450, 150, 20));
            // Percorre a lista inicializando todos objetos incluídos.
            for (Entidade e : entidades) {
                e.init();
            }
        } catch (IOException ex) {
        }
    }

    @Override
    public void onUnload() {
        try {
            AudioManager.getInstance().loadAudio("chilla_dream_pad_1_130bpm.wav").stop();
        } catch (IOException ex) {
        }
    }

    @Override
    public void onUpdate(int currentTick) {
        if (!gameOver) {
            for (Entidade e : entidades) {
                e.update(currentTick);
            }
            collisionDetector.update(currentTick);
        }
        if (InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
            terminate();
        }
    }

    @Override
    public void onRender(Graphics2D g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 800, 600);
        for (Entidade e : entidades) {
            e.render(g);
        }
    }
}
