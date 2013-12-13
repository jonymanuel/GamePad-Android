package com.gamepad;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
<<<<<<< HEAD
=======
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
>>>>>>> e74965abb3789fb379701562f5a46291d47e064b


public class TestMainActivity extends AndroidApplication {

<<<<<<< HEAD
=======
    private TestGame testGame = new TestGame();

>>>>>>> e74965abb3789fb379701562f5a46291d47e064b
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        initialize(testGame, cfg);
>>>>>>> e74965abb3789fb379701562f5a46291d47e064b
    }

}

