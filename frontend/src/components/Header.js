import React from 'react';
import {AppBar, Toolbar, Typography} from "@mui/material";


const Header = () => {
    return (
        <AppBar position="static" style={{background: '#2b2b2b'}}>
            <Toolbar >
                <Typography variant="h6" style={{ fontWeight: 'bold', fontSize: '1.5rem' }}>
                    🇲🇰 Macedonian Legacy
                </Typography>
            </Toolbar>
        </AppBar>
    );
}

export default Header;
