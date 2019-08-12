import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import { Link } from 'react-router-dom';
import Container from 'muicss/lib/react/container';
import Row from 'muicss/lib/react/row';
import Col from 'muicss/lib/react/col';
import ItemsNavigation from './ItemsNavigation';


const rowStyle={
  margin: 0,
}

const containerStyle={
  padding: 0,
}

class App extends Component {
  constructor(){
    super();
    this.state={
      open: true,
            leftDrop: false,
            anchorOrigin: { horizontal: 'left',
                          vertical: 'bottom' },
            targetOrigin:{ horizontal: 'left', 
                          vertical: 'top' }
    }
  }

  render() {
    return (
      <MuiThemeProvider>
            <div>
            <Container fluid={true} style={containerStyle}>
                <Row style={rowStyle}>
                <Col md="12">
                <AppBar 
                  className="helloUser"
                  showMenuIconButton={false}> 
                </AppBar>
                </Col>
                </Row>
                </Container>
              <Container fluid={true} style={containerStyle}>
              <Row style={rowStyle}>
                <Col md="2">
                  <Drawer open={this.state.open} width={150}>
                    <AppBar showMenuIconButton={false}></AppBar>
                    
                    <MenuItem primaryText="Enter New Text"
                              containerElement={<Link to="/newText" />}
                              /><br/>   
                    <MenuItem primaryText="Text Analyzer"
                              containerElement={<Link to="/texts" />}
                              /><br/>                  
                   <MenuItem primaryText="Words History"
                              containerElement={<Link to="/wordsGroups" />}
                              /><br/>        
                  </Drawer>
                </Col>
                <Col md="10">
                    <ItemsNavigation/>
                </Col>
              </Row>
              </Container> 

            </div>
            </MuiThemeProvider>
    );
  }
}

export default App;
