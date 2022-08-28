import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";


const callRestApi = async (shortUrl) => {
    const response = await fetch(`http://localhost:8080/api/v1/manage/${shortUrl}`);
    const data = await response.json();
    return data;
};


function MainTable() {

    let params = window.location.pathname.slice(1);

    const [apiResponse, setApiResponse] = useState("*** now loading ***");

    useEffect(() => {
        callRestApi(params).then(
            result => {
                setApiResponse(result)
            });
    }, []);

    return (

        <div className="mt-5">
            <p>{apiResponse.shortUrl} | {apiResponse.owner} | {apiResponse.tags} | {apiResponse.longUrl} | {apiResponse.createdAt}</p>
        </div>

    )
}

export default MainTable