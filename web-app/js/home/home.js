function onYoutubeReady() {

  console.log("teste");

  $.ajax({
    url: 'music/lastAdded',
    success: onGetLastAddedMusic
  });

  $.ajax({
    url: 'album/lastAdded',
    success: function(result) {
      onGetLastAddedAlbum(JSON.parse(result));
    }
  });

  $.ajax({
    url: 'authorship/lastAdded',
    success: function(result) {
      onGetLastAddedAuthorship(JSON.parse(result));
    }
  });

}

function onGetLastAddedMusic(result) {
  if (result) {
    startPlayer("mainPlayer", result);
    $("#lastAddedMusic").show();
  }
}

function onGetLastAddedAlbum(result) {
  if (result) {
    $("#lastAddedAlbum").show();
    $("#lastAddedAlbumName").text(result.name);
    $("#lastAddedAlbumPage").attr('href', result.page);
  }
}

function onGetLastAddedAuthorship(result) {
  if (result) {
    $("#lastAddedAuthorship").show();
    $("#lastAddedAuthorshipName").text(result.name);
    $("#lastAddedAuthorshipPage").attr('href', result.page);
  }
}
