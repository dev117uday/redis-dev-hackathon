import React, { useState } from 'react';

function SaveForm(props) {

    const [shortLink, setShortLink] = useState();
    const [longLink, setLongLink] = useState();
    const [owner, setOwner] = useState();
    const [tag, setTag] = useState();

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(shortLink + ":" + longLink + ":" + owner + ":" + tag);

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "shortUrl": shortLink,
            "owner": owner,
            "tags": [
                tag
            ],
            "longUrl": longLink
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("http://localhost:8080/api/v1/manage/", requestOptions)
            .then(response => response.text())
            .then(result => {
                alert("success in saving")
                window.location.href = "/"
            })
            .catch(error => console.log('error', error));

    }

    return (
        <form onSubmit={e => { handleSubmit(e) }}>
            <div class="mb-2">
                <label class="form-label">Short Link</label>
                <input type="text" class="form-control" onChange={(ele) => {
                    setShortLink(ele.target.value)
                }
                } />
            </div>
            <div class="mb-2">
                <label class="form-label">Long URL</label>
                <input type="text" class="form-control" onChange={(ele) => setLongLink(ele.target.value)} />
            </div>
            <div class="mb-2">
                <label class="form-label">Owner : </label>
                <input type="text" class="form-control" onChange={(ele) => setOwner(ele.target.value)} />
            </div>
            <div class="mb-2">
                <label class="form-label">Tag or Label</label>
                <input type="text" class="form-control" onChange={(ele) => setTag(ele.target.value)} />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    )
}

export default SaveForm;