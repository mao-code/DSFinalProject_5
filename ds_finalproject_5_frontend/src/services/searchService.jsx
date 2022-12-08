import { serverConfig } from '../config.js';
import axios from 'axios';

const SearchService = {
    search: async function(keyword){
        // fetch search api in java spring boot
        let { domain, port, route, category, action } = serverConfig;
        let uri = `http://${domain}:${port}/${route.search}/${category.movie}/${action.search}/${keyword}`;
        try{
            const res = await axios.get(uri);
            return res.data;
        }catch(error){
            console.log(error);
        }
             
    }
};

export default SearchService;