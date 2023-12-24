import axios from "../custom-axios/axios";


const AxiosService = {

    findAll: () => {
        return axios.get("/locations/findAll");
    },

    search: (filter) => {
        return axios.post("/locations/search", {
            name: filter.name,
            category: filter.category,
            cityName: filter.city
            //Final version will have combination of categories and cities
        })
    },

    getCityData: () => {
        return axios.get("/cities")
    }
        
}
export default AxiosService