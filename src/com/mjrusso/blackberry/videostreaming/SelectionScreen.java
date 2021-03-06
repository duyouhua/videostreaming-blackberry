package com.mjrusso.blackberry.videostreaming;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;

public class SelectionScreen extends MainScreen implements FieldChangeListener
{

    private HorizontalFieldManager _hfm;
    private ButtonField _startButton;
    private ObjectChoiceField _playbackChoice;
    private BasicEditField _urlField;

    public SelectionScreen()
    {
        setTitle(new LabelField("Video Streaming", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH));

        PlaybackChoice choices[] = {
              new BrowserPlaybackChoice("HTTP/ RTSP via Browser")
            , new MMAPIPlaybackChoice("HTTP/ RTSP via MMAPI")
            , new StreamingPlayerPlaybackChoice("HTTP via StreamingPlayer")
        };

        _playbackChoice = new ObjectChoiceField("Playback Type", choices);
        add(_playbackChoice);

        _urlField = new BasicEditField("Playback URL\n", "http://mjrusso.com/bb/bb.mp4", 255, Field.FIELD_RIGHT);
        _urlField.setChangeListener(this);
        add(_urlField);

        _hfm = new HorizontalFieldManager(Field.FIELD_HCENTER);
        _startButton = new ButtonField("Start");
        _startButton.setChangeListener(this);
        _hfm.add(_startButton);
        add(_hfm);
    }

    public void fieldChanged(Field field, int context)
    {
        if (field == _startButton)
        {
            PlaybackChoice selection = (PlaybackChoice) _playbackChoice.getChoice(
                _playbackChoice.getSelectedIndex()
            );
            selection.play(_urlField.getText());
        }
        else if (field == _playbackChoice) { }
        else if (field == _urlField) { }
        else { }
    }

    protected boolean onSavePrompt()
    {
        return true;
    }

    public void close()
    {
        System.exit(0);
        super.close();
    }
}
