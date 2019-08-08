package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.ParallelAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CenterBackupPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftFarHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardLeftToLeftFar extends AutoMode {
    public ForwardLeftToLeftFar(Position2d startPos){
        super(startPos);
        setStartDirection(AutoMode.StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftFarHatchPath(startingPos, Path.Direction.FORWARD)),
                //Vision
                //new LineUpAction(false),
                // when true, ground outtake action
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathAction(new CenterBackupPath())
                ))
        )));
    }
}
