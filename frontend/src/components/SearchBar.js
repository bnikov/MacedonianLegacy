import React, {useEffect, useState} from 'react';
import {Button, MenuItem, Select, TextField} from "@mui/material";
import AxiosService from "./ repository/AxiosService";

const SearchBar = ({onSearch}) => {
    const [searchFilter, setSearchFilter] = useState({
        name: '',
        category: [],
        city: [],
    });

    const [cityData, setCityData] = useState();

    useEffect(() => {
        AxiosService.getCityData()
            .then(resp => {
                console.log(resp.data.flat());
                setCityData(resp.data.flat());
            })
            .catch(error => {
                console.error("Error fetching city data:", error);
            });
    }, []);

    const renderValue = (selected, type) => {
        if (selected.length === 0) {
            return type === 'category' ? 'Category' : 'City';
        } else if (selected.length === 1) {
            return selected[0];
        } else {
            return `${selected.length}+`;
        }
    };

    const handleSearch = () => {
        onSearch(searchFilter);
    };

    const handleReset = () => {
        setSearchFilter({
            name: '',
            category: [],
            city: [],
        });
    };

    return (
        <div
            style={{
                width: '75%',
                padding: '16px',
                borderRadius: '8px',
                margin: 'auto',
                marginTop: '50px',
                boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.5)',
            }}
        >
            <div style={{display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
                <TextField
                    label="Name"
                    value={searchFilter.name}
                    onChange={(e) => setSearchFilter((prev) => ({...prev, name: e.target.value}))}
                    margin="dense"
                    variant="outlined"
                />
                <Select
                    value={searchFilter.category}
                    onChange={(e) => setSearchFilter((prev) => ({...prev, category: e.target.value}))}
                    displayEmpty
                    renderValue={(selected) => renderValue(selected, 'category')}
                    multiple
                    margin="dense"
                >
                    <MenuItem value="" disabled>
                        Category
                    </MenuItem>
                    <MenuItem value="Археолошки Локалитет">Археолошки Локалитет</MenuItem>
                    <MenuItem value="Светилиште">Светилиште</MenuItem>
                    <MenuItem value="Замок">Замок</MenuItem>
                    <MenuItem value="Гробница">Гробница</MenuItem>
                    <MenuItem value="Авион">Авион</MenuItem>
                    <MenuItem value="Споменик">Споменик</MenuItem>
                    <MenuItem value="Градска порта">Градска порта</MenuItem>
                    <MenuItem value="Урнатини">Урнатини</MenuItem>
                    <MenuItem value="Бојно поле">Бојно поле</MenuItem>
                    <MenuItem value="Локомотива">Локомотива</MenuItem>
                    <MenuItem value="Граничен Камен">Граничен Камен</MenuItem>
                    <MenuItem value="Спомен">Спомен</MenuItem>
                </Select>
                <Select
                    value={searchFilter.city}
                    onChange={(e) => setSearchFilter((prev) => ({...prev, city: e.target.value}))}
                    displayEmpty
                    renderValue={(selected) => renderValue(selected, 'city')}
                    multiple
                    margin="dense"
                >
                    <MenuItem value="" disabled>
                        City
                    </MenuItem>
                    {cityData?.map((city) => (
                        <MenuItem key={city.cityName} value={city.cityName}>{city.cityName}</MenuItem>
                    ))}
                </Select>

                <div>
                    <Button variant="outlined" color="secondary" onClick={handleReset}>
                        Reset
                    </Button>

                    <Button variant="contained" color="primary" onClick={handleSearch}>
                        Search
                    </Button>
                </div>
            </div>
        </div>
    );
}

export default SearchBar;
