import axios from 'axios';

const instance = axios.create({
    baseURL: 'https://dians-api-3ikxnodbya-lm.a.run.app/api',
    headers: {
        'Access-Control-Allow-Origin' : '*',
    }
})
export default instance;