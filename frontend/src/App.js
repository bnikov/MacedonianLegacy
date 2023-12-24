import './App.css';
import Header from "./components/Header";
import SearchBar from "./components/SearchBar";
import React, {useEffect, useState} from "react";
import {createTheme, ThemeProvider} from "@material-ui/core";
import {makeStyles} from "@mui/styles";
import LocationTable from "./components/Locations";
import AxiosService from "./components/ repository/AxiosService";


const theme = createTheme(
    {
        palette: {
            mode: "dark"
        },
    });

const useStyles = makeStyles((theme) => {
});

function App() {
    const classes = useStyles();
    const [locations, setLocations] = useState([])

    useEffect(() => {
        AxiosService.findAll()
            .then(resp => {
                console.log(resp.data);
                setLocations(resp.data);
            })
            .catch(/* TODO: Catch */);
    }, []);

    const onSearch = (filter) => {
        AxiosService.search(filter)
            .then(resp => setLocations(resp.data))
            .catch(/* TODO Catch */)
    }

    return (
        <ThemeProvider theme={theme}>
            <Header/>
            <SearchBar onSearch={onSearch}/>
            <LocationTable locations={locations}/>
        </ThemeProvider>
    )
}

export default App;
