import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

class TextComponet extends Component{

    constructor(props) {
        super(props);
        this.state = {
            open: false
        };
    }

    render() {
        if (!this.props.textComponetInfo){
            return null;
        }

        const actions =
            (<FlatButton
                label="Close"
                primary={true}
                onClick={this.props.closeAction}
            />);

            var oneItem = (
                <div id="textComponetInfo">
                 <p>{this.props.textComponetInfo.textField}</p>             
                </div>
            )

        return (
            <MuiThemeProvider>
                <div>
                    <Dialog
                        title="Full text"
                        actions={actions}
                        modal={true}
                        open={this.props.open}
                    >
                        {oneItem}
                    </Dialog>

                </div>
            </MuiThemeProvider>
        );
    }

}export default TextComponet;