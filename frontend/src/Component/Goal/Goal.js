import React, {Component} from 'react';
import '/Users/studenteng01/Documents/codeDIFF/Project/SpyGlass(vanGuard)/frontend/src/Component/Goal/Goal.css'
import postImage from '/Users/studenteng01/Documents/codeDIFF/Project/SpyGlass(vanGuard)/frontend/src/images/post.jpg';
import commentIcon from '/Users/studenteng01/Documents/codeDIFF/Project/SpyGlass(vanGuard)/frontend/src/images/commentIcon.png';
class Goal extends Component{
    constructor(props){
        super(props)
        this.state ={}
    }
    render(){
        return(
            <div
                className = "goal_container">
                <div className ="goal_header">
                    <div className = "goal_name">
                        GoalName
                    </div>
                </div>
                <div>
                    <img src ={postImage} width="600px"></img>
                </div>
                <div>
                    <div className = "goal_analytics">
                        <img src ={commentIcon}></img>

                    </div>
                </div>
                <div>
                    {/* comments */}
                </div>

            </div>
        );
    }
}

export default Goal;